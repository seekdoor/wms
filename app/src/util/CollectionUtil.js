export default {

    /**
     * 深度查找对象是否存在
     * @param collection    集合
     * @param find  回调函数
     * @param childKey  子集合字段名称
     * @returns {null}
     */
    findDeep(collection, find, childKey = 'children') {
        let res = null;
        res = collection.find(find) || null;
        if (res) return res;
        for (let i = 0; i < collection.length; i++) {
            let D = collection[i];
            if (D[childKey] && D[childKey].length > 0) {
                res = this.findDeep(D[childKey], find, childKey);
                if (res) break;
            }
        }
        return res;
    },

    /**
     * 查找集合是否存在
     * 返回集合 index 值
     * @param collection
     * @param find
     * @returns {number}
     */
    findIndex(collection, find) {
        let res = -1;
        for (let i = 0; i < collection.length; i++) {
            let C = collection[i];
            let r = true;
            for (let j in find) {
                let D = find[j];
                if (typeof C[j] !== "undefined" && C[j] === D) continue;
                r = false;
                break;
            }
            if (r) res = i;
        }
        return res;
    },

    /**
     * 一般数组转换为树状结构
     * @param list
     * @param pid
     * @param idKey
     * @param pidKey
     * @param childKey
     * @returns {Array}
     */
    listToTree(list, pid = 0, idKey = 'id', pidKey = 'pid', childKey = 'children') {
        let res = [];
        for (let i in list) {
            let D = list[i];
            if (D[pidKey] === pid) {
                let r = this.listToTree(list, D[idKey], idKey, pidKey, childKey);
                if (r.length) D[childKey] = r;
                res.push(D);
            }
        }
        return res;
    },

    /**
     * 树状结构筛选
     * 需要保证还有子节点的父节点不被过滤
     * @param tree
     * @param filter
     * @param idKey
     * @param childKey
     * @param deep
     * @param nowDeep
     * @returns {Array}
     */
    filterTree(tree, filter, childKey = 'children', deep = null , nowDeep = 0) {
        let res = [];
        for (let leaf of tree) {
            let show = (deep === null || deep === nowDeep + 1) && filter(leaf);
            if (leaf.children && leaf.children.length) {
                leaf.children = this.filterTree(leaf.children, filter, childKey, deep, nowDeep + 1);
            }
            if (show || (leaf.children && leaf.children.length)) {
                res.push(leaf);
            }
        }
        return res;
    },


    /**
     * 将树状结构扁平化
     * @param tree
     * @param childKey
     * @returns {Array}
     */
    platTrees(tree, childKey = 'children') {
        let res = [];
        for (let i in tree) {
            let D = tree[i];
            res.push(res);
            if (D[childKey]) {
                res.concat(this.platTrees(D[childKey, childKey]));
            }
        }
        return res;
    },

    /**
     * 查询路径
     * @param tree
     * @param find
     * @param key
     * @param childKey
     * @param deep
     * @param nowDeep
     * @returns {Array}
     */
    getTreeRoute(tree, find, key = 'id', childKey = null, deep = null, nowDeep = 0) {
        let ret = [];
        childKey = childKey || 'children';
        tree.forEach(x => {
            let is = (deep === null || deep === nowDeep + 1) && find(x);
            if (is) {
                ret.push(x[key]);
            } else if (x[childKey] && x[childKey].length) {
                let r = this.getTreeRoute(x[childKey], find, key, childKey, deep, nowDeep + 1);
                if (r.length) {
                    ret = [x[key], ...r];
                }
            }
        });
        return ret;
    },

    /**
     * 并集
     * @param coll1
     * @param coll2
     * @returns {...*[]}
     */
    merge(coll1, coll2) {
        return [...new Set([...coll1, ...coll2])];
    },

    /**
     * 差集
     * @param coll1
     * @param coll2
     * @param diff
     * @returns {Array}
     */
    diff(coll1, coll2, diff = true) {
        let ret = [];
        coll1.forEach(x1 => {
            let t = coll2.find(x2 => x2 === x1);
            if (diff && !t) {
                ret.push(x1);
            }
            if (!diff && t) {
                ret.push(x1);
            }
        });
        coll2.forEach(x1 => {
            let t = coll1.find(x2 => x2 === x1);
            if (diff && !t) {
                ret.push(x1);
            }
            if (!diff && t) {
                ret.push(x1);
            }
        });
        return ret;
    },

    /**
     * 交集
     * @param coll1
     * @param coll2
     * @returns {*|Array}
     */
    intersect(coll1, coll2) {
        return this.diff(coll1, coll2, false);
    },

    /**
     * 交集和并集
     * 如果存在交集 这取差集
     * 如果不存在交集 则取并集
     * @param coll1
     * @param coll2
     * @returns {*|Array|*}
     */
    mergeDiff(coll1, coll2) {
        let m = this.merge(coll1, coll2);
        if (m.length > coll1.length && m.length > coll2.length) {
            return m;
        }
        let t = coll1.length > coll2.length ? coll2 : coll1;
        return this.diff(m, t);
    },
    /**
     * 将两个有所属关系的列表合并为树状结构
     * @param parentList
     * @param childList
     * @param childKey
     * @param idKey
     * @param pid
     * @returns {Array|*[]}
     */
    listToTreeByTwoList(parentList = [], childList = [], pid = 'id', childKey = 'children', idKey = 'id') {
        if (!childList.length) return parentList;
        return parentList.map(p => {
            let c = {};
            c[childKey] = childList.filter(ch => ch[pid] === p[idKey]);
            return Object.assign(p, c);
        });
    }

}
