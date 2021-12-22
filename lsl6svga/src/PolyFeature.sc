;;; Sierra Script 1.0 - (do not remove this comment)
(script# 95)
(include sci.sh)
(use Feature)
(use System)


(class PolyFeature of Feature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 5
		z 0
		listObj 0
		variableX 0
		variableY 0
	)
	
	(method (dispose)
		(if listObj (listObj dispose:))
		(super dispose:)
	)
	
	(method (onMe theObjOrX &tmp temp0 listObjFirst temp2)
		(= listObjFirst (listObj first:))
		(while listObjFirst
			(= temp2 (NodeValue listObjFirst))
			(if (temp2 onMe: (theObjOrX x?) (theObjOrX y?))
				(if variableX (= x (theObjOrX x?)))
				(if variableY (= y (theObjOrX y?)))
				(return 1)
			)
			(= listObjFirst (listObj next: listObjFirst))
		)
		(return 0)
	)
	
	(method (addPolygon theListObj &tmp temp0)
		(cond 
			(([theListObj 0] isKindOf: Collect) (= listObj [theListObj 0]) (return))
			((not listObj) ((= listObj (Set new:)) add:))
		)
		(= temp0 0)
		(while (< temp0 argc)
			(listObj add: [theListObj temp0])
			(if (not ([theListObj temp0] points?))
				([theListObj temp0] init:)
			)
			(++ temp0)
		)
	)
)
