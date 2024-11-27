/**
 * Purpose: a patch for zkcal-124
 * Based on version: 3.1.2
 */
zk.afterLoad('calendar', function() {
    var exWidget = {};
    zk.override(calendar.Item.prototype, exWidget, {
        _createBoundTime: function (node, bd, ed) {
            //have findBoundTime_ function
            if (this.findBoundTime_) {
                var time = this.findBoundTime_(bd, ed);
                bd = time.bd;
                ed = time.ed;
            }
            node.upperBoundBd = this._setBoundDate(bd); // earliest
            if (this._isDayItem()) return;
            if (isLessThan30Min(bd, ed)){
                node.lowerBoundEd = calUtil.addDay(new Date(ed), 1);
            }else{
                node.lowerBoundEd = this._setBoundDate(ed, true); // latest
            }
        },
    });

    function isLessThan30Min(beginDate, endDate) {
        return (Math.abs(endDate - beginDate) <= 1800000);
    }
});