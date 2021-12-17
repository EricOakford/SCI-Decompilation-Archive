;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50)
(include sci.sh)
(use Cursor)


(class HotCursor of Cursor
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
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view 0
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $5021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		hidden 0
		hilited 0
		curType 1
		invItem 0
		verb 2
		cursorView 0
		DirCursor 0
		normalCursorView 0
	)
	
	(method (init param1)
		(if argc
			(self normalCursorView: param1)
			(self cursorView: normalCursorView)
			(= view (cursorView view?))
			(= loop (cursorView loop?))
			(= cel (cursorView cel?))
		)
		(super init:)
	)
	
	(method (handleEvent event)
		(cond 
			((event type?) 0)
			((event claimed?) 0)
			(else
				(if invItem
					(self setInvCursor:)
				else
					(self setNormalCursor:)
				)
				(self lolite:)
			)
		)
	)
	
	(method (hilite)
		(if
			(and
				(not (self isDirCursor:))
				(not hilited)
				(!= curType 4)
			)
			(= hilited 1)
			(++ cel)
			(super init:)
		)
	)
	
	(method (lolite param1)
		(if (or hilited (and argc param1))
			(= hilited 0)
			(= view (cursorView view?))
			(= loop (cursorView loop?))
			(= cel (cursorView cel?))
			(super init:)
		)
	)
	
	(method (setInvCursor theInvItem)
		(if argc ((= invItem theInvItem) owner: -3))
		(= curType 3)
		(= verb (invItem verb?))
		(if (!= cursorView invItem)
			(= cursorView invItem)
			(self lolite: 1)
		)
	)
	
	(method (setDirCursor theCursorView)
		(= curType 2)
		(= verb 9)
		(if (!= cursorView theCursorView)
			(= cursorView theCursorView)
			(self lolite: 1)
		)
	)
	
	(method (setNormalCursor theCursorView &tmp theNormalCursorView)
		(= curType 1)
		(= verb 2)
		(= invItem 0)
		(= theNormalCursorView normalCursorView)
		(cond 
			(argc
				(if (!= cursorView theCursorView)
					(= cursorView theCursorView)
					(self lolite: 1)
				)
			)
			((!= cursorView normalCursorView) (= cursorView normalCursorView) (self lolite: 1))
		)
		(return theNormalCursorView)
	)
	
	(method (setTempCursor theCursorView theVerb)
		(if (!= cursorView theCursorView)
			(= curType 4)
			(= cursorView theCursorView)
			(self lolite: 1)
		)
		(if (!= verb theVerb) (= verb theVerb))
	)
	
	(method (isNormalCursor)
		(return (== curType 1))
	)
	
	(method (isDirCursor)
		(return (== curType 2))
	)
	
	(method (isInvCursor)
		(return (== curType 3))
	)
)
