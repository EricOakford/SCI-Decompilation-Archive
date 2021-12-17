;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64882)
(include sci.sh)
(use Intrface)
(use List)
(use Event)
(use AnimPic)
(use TextView)
(use String)
(use System)


(class aeAnimPlayerClass of AnimPlayer
	(properties
		scratch 0
		cuee 0
		normalizeRect 0
		oMyFeatures 0
		notifyDispose 0
		setScaleDirect 0
		setScalePercent 0
		getMainCast 0
		whoToTurn 0
		whatToFace 0
		whoToCue 0
		playing 0
		facer 0
		approach 0
		approachThenFace 0
		face 0
		normalize 0
		stopwalk 0
		getHeight 100
		getWidth 0
		findCastMember 0
		initItems 0
		isEnabled 0
		bBorder 0
		nSizeMode 0
	)
	
	(method (load)
		(if normalizeRect
			(normalizeRect dispose:)
			(= normalizeRect 0)
		)
		(super load: &rest)
	)
	
	(method (nTilesX param1)
		(super nTilesX: param1)
		(if
		(and whoToCue (not (whoToCue nRightBorderWidth?)))
			((whoToCue nTopBorderHeight?)
				nBottomBorderHeight: param1
			)
		)
	)
	
	(method (nTilesY)
	)
	
	(method (nBorderHeight &tmp temp0)
		(if (or (not findCastMember) (>= face initItems))
			(return)
		)
		(= temp0 (self nOffsetY: (+ face 4)))
		(self nTilesX: temp0)
	)
	
	(method (nBorderWidth &tmp theApproachThenFace_2 temp1 temp2 theTheTheNormalize theTheNormalize theApproachThenFace theNormalize theFace)
		(if
		(or (not findCastMember) (<= face approachThenFace))
			(return)
		)
		(= theNormalize (= theTheNormalize 0))
		(= theFace (= theApproachThenFace approachThenFace))
		(= theApproachThenFace_2 approachThenFace)
		(while (< theApproachThenFace_2 initItems)
			(= temp2 (self nOffsetY: (+ theApproachThenFace_2 2)))
			(if
				(>=
					(= theTheTheNormalize
						(self nOffsetY: (+ theApproachThenFace_2 4))
					)
					normalize
				)
				(break)
			)
			(if (!= theTheTheNormalize theTheNormalize)
				(= theFace theApproachThenFace)
				(= theNormalize theTheNormalize)
				(= theApproachThenFace theApproachThenFace_2)
				(= theTheNormalize theTheTheNormalize)
			)
			(= theApproachThenFace_2
				(+ theApproachThenFace_2 4 temp2)
			)
		)
		(= normalize theNormalize)
		(= face theFace)
		(self nTilesX: normalize)
	)
	
	(method (nLeftBorderWidth theNormalize &tmp theApproachThenFace temp1 temp2)
		(if
		(or (not findCastMember) (<= face approachThenFace))
			(return)
		)
		(= theApproachThenFace approachThenFace)
		(while (< theApproachThenFace initItems)
			(= temp1 (self nOffsetY: (+ theApproachThenFace 2)))
			(if
				(>=
					(= temp2 (self nOffsetY: (+ theApproachThenFace 4)))
					theNormalize
				)
				(= normalize theNormalize)
				(= face theApproachThenFace)
				(break)
			)
			(= theApproachThenFace (+ theApproachThenFace 4 temp1))
		)
		(self nTilesX: normalize)
	)
)

(class aeCommand of Obj
	(properties
		scratch 0
		type $ffff
		size 4
		vTiles 0
		whoToCue 0
	)
	
	(method (init theVTiles)
		(= vTiles theVTiles)
	)
	
	(method (save param1)
		(if (== -1 type)
			(MonoOut {aeCommand: UNSET type})
			(SetDebug)
		)
		(param1 writeWord: type)
		(param1 writeWord: size)
		(param1 writeWord: vTiles)
		(param1 writeWord: (whoToCue getBitmap?))
	)
	
	(method (resize param1 param2)
		(if (== vTiles param1) (self save: param2))
	)
)

(class aeCastMember of Obj
	(properties
		scratch 0
		whoToCue 0
		getBitmap 0
		voBG -1
		oSpecialSync -1
		nSpecialSelector 0
		nCutoff 0
		bSpecial 0
		type $ffff
		back 234
	)
	
	(method (init)
		(super init:)
		(= bSpecial (List new:))
		(if (not nSpecialSelector)
			(= nSpecialSelector (Str new:))
			(nSpecialSelector format: {%s%d} name getBitmap)
		)
		((= nCutoff (Str new:)) copy: nSpecialSelector)
	)
	
	(method (dispose)
		(bSpecial dispose:)
		(super dispose:)
	)
	
	(method (edit)
		(MonoOut {Object %s needs a edit method.} name)
	)
	
	(method (doSelect)
		(MonoOut {Object %s needs a editCommands method.} name)
	)
	
	(method (doClick)
		(MonoOut {Object %s needs a help method.} name)
	)
	
	(method (doHeld param1 &tmp temp0)
		(= temp0 (param1 new:))
		(temp0 init: &rest)
		(self drawButton: temp0)
	)
	
	(method (drawButton param1)
		(self nSelectMethod: (param1 -super-?) (param1 vTiles?))
		(bSpecial add: param1)
		(param1 whoToCue: self)
		(self nClickMethod:)
		(self bIsHighlighted: param1)
	)
	
	(method (stopHogging param1 &tmp bSpecialFirst temp1)
		(= bSpecialFirst (bSpecial first:))
		(while (> bSpecialFirst 0)
			(= temp1 (KList 8 bSpecialFirst))
			(temp1 vTiles: (+ (temp1 vTiles?) param1))
			(= bSpecialFirst (bSpecial next: bSpecialFirst))
		)
		(= voBG (+ voBG param1))
		(= oSpecialSync (+ oSpecialSync param1))
		(self bIsHighlighted:)
	)
	
	(method (internalDoSelect param1)
		(bSpecial delete: param1)
		(param1 dispose:)
		(self nClickMethod:)
		(self bIsHighlighted: param1)
	)
	
	(method (oSelectNotify param1 &tmp bSpecialFirst temp1)
		(= bSpecialFirst (bSpecial first:))
		(while (> bSpecialFirst 0)
			(if
			((= temp1 (KList 8 bSpecialFirst)) isKindOf: param1)
				(self internalDoSelect: temp1)
				(break)
			)
			(= bSpecialFirst (bSpecial next: bSpecialFirst))
		)
	)
	
	(method (nSelectMethod param1 param2 &tmp bSpecialFirst temp1)
		(= bSpecialFirst (bSpecial first:))
		(while (> bSpecialFirst 0)
			(= temp1 (KList 8 bSpecialFirst))
			(if
				(and
					(== param2 (temp1 vTiles?))
					(temp1 isKindOf: param1)
				)
				(self internalDoSelect: temp1)
				(break)
			)
			(= bSpecialFirst (bSpecial next: bSpecialFirst))
		)
	)
	
	(method (nSelectValue param1)
		(bSpecial firstTrue: #isKindOf param1)
	)
	
	(method (oClickNotify theVoBG)
		(cond 
			((== voBG -1) (= oSpecialSync (= voBG theVoBG)))
			((> theVoBG oSpecialSync) (= oSpecialSync theVoBG))
		)
	)
	
	(method (nClickMethod &tmp bSpecialFirst temp1)
		(= voBG (= oSpecialSync -1))
		(= bSpecialFirst (bSpecial first:))
		(while bSpecialFirst
			(= temp1 (KList 8 bSpecialFirst))
			(self oClickNotify: (temp1 vTiles?))
			(= bSpecialFirst (bSpecial next: bSpecialFirst))
		)
	)
	
	(method (save)
		(MonoOut {Object %s needs a save method.} name)
	)
	
	(method (oHeldNotify param1 param2)
		(bSpecial eachElementDo: #resize param2 param1)
	)
	
	(method (nHeldMethod param1)
		(return (== getBitmap param1))
	)
	
	(method (bIsHighlighted)
		(whoToCue unregisterEventHog: 1)
	)
	
	(method (bImAHog param1 &tmp whoToCueNTopBorderHeight)
		((= whoToCueNTopBorderHeight (whoToCue nTopBorderHeight?))
			oMyGroup: 0 param1 back nSpecialSelector
		)
		(whoToCueNTopBorderHeight
			isButtonDown: param1 voBG oSpecialSync
		)
	)
	
	(method (handleEvent)
		(MonoOut {Object %s needs a handleEvent method.} name)
	)
	
	(method (onMe theObjOrX &tmp whoToCueNTopBorderHeight temp1)
		(= whoToCueNTopBorderHeight (whoToCue nTopBorderHeight?))
		(theObjOrX localize: (whoToCueNTopBorderHeight plane?))
		(if
			(whoToCueNTopBorderHeight
				registerEventHog: theObjOrX self
			)
			(return
				(if
					(<=
						voBG
						(= temp1
							(whoToCueNTopBorderHeight bButtonDown: (theObjOrX x?))
						)
					)
					(<= temp1 oSpecialSync)
				else
					0
				)
			)
		)
		(return 0)
	)
	
	(method (bEnabled &tmp whoToCueNTopBorderHeight temp1 temp2 temp3 theVoBG)
		(= temp1
			((= whoToCueNTopBorderHeight (whoToCue nTopBorderHeight?))
				plane?
			)
		)
		(= temp2 (whoToCueNTopBorderHeight setButton: self))
		((= temp3 (Event new: -32768)) localize: temp1)
		(= theVoBG voBG)
		(repeat
			((= temp3 (Event new: -32768)) localize: temp1)
			(whoToCueNTopBorderHeight
				isButtonDown: temp2 theVoBG (+ (- oSpecialSync voBG) theVoBG) 1
			)
			(= theVoBG
				(whoToCueNTopBorderHeight bButtonDown: (temp3 x?))
			)
			(whoToCueNTopBorderHeight
				isButtonDown: temp2 theVoBG (+ (- oSpecialSync voBG) theVoBG) 0
			)
			(temp3 dispose:)
			(FrameOut)
			(breakif (not (StillDown)))
		)
		(self stopHogging: (- theVoBG voBG))
	)
)

(class aeCastList of List
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
	)
	
	(method (setGroup param1 &tmp temp0)
		(= temp0 (self firstTrue: #nHeldMethod param1))
	)
	
	(method (save param1 &tmp aeCastListFirst temp1 temp2 temp3)
		(param1 writeWord: 3)
		(param1 writeWord: 100)
		(= temp2 -1)
		(= aeCastListFirst (self first:))
		(while aeCastListFirst
			((= temp1 (KList 8 aeCastListFirst)) save: param1)
			(if (< temp2 (temp1 oSpecialSync?))
				(= temp2 (temp1 oSpecialSync?))
			)
			(= aeCastListFirst (self next: aeCastListFirst))
		)
		(= temp3 0)
		(while (<= temp3 temp2)
			(= aeCastListFirst (self first:))
			(while aeCastListFirst
				((= temp1 (KList 8 aeCastListFirst))
					oHeldNotify: param1 temp3
				)
				(= aeCastListFirst (self next: aeCastListFirst))
			)
			(++ temp3)
		)
	)
)

(class aeStatusBarClass of TextView
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
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $4021
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
		text 0
		font 0
		nBaseHeight 0
		fore 234
		back 220
		skip 0
		width 300
		height 20
		margin 2
	)
	
	(method (onMe)
		(return 0)
	)
)
