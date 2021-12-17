;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64988)
(include game.sh)
(use Main)
(use ScrollView)
(use Motion)
(use Actor)

(public
	proc64988_0 0
)

(procedure (proc64988_0 param1 &tmp temp0 temp1 temp2 temp3)
	(= temp0 (param1 plane?))
	(if (temp0 isKindOf: ScrollPlane)
		(= temp1 (temp0 getLocalX: param1))
		(= temp2 (temp0 getLocalY: param1))
		(= temp3
			(MulDiv
				(CelHigh (param1 view?) 0 0)
				(param1 scaleY?)
				128
			)
		)
		(if gOFileReadWord_7
			(= global205 240)
			(= global206 60)
		else
			(= global205 50)
			(= global206 12)
			(= global109
				(- (- (temp0 nScreenSizeX?) 5) (* global205 2))
			)
			(= global110
				(-
					(- (temp0 nScreenSizeY?) 5)
					(+ (* 2 global206) temp3)
				)
			)
		)
		(cond 
			(
			(and (temp0 canScrollLeft:) (< temp1 global205))
				(if gOFileReadWord_7
					(temp0 scroll: (- temp1 global205) 0)
				else
					(temp0 scroll: (- 0 global109) 0)
				)
			)
			(
				(and
					(temp0 canScrollRight:)
					(> temp1 (- (temp0 nScreenSizeX?) global205))
				)
				(if gOFileReadWord_7
					(temp0
						scroll: (- temp1 (- (temp0 nScreenSizeX?) global205)) 0
					)
				else
					(temp0 scroll: global109 0)
				)
			)
		)
		(cond 
			(
				(and
					(temp0 canScrollUp:)
					(< (- temp2 temp3) global206)
				)
				(if gOFileReadWord_7
					(temp0 scroll: 0 (- (- temp2 temp3) global206))
				else
					(temp0 scroll: 0 (- 0 global110))
				)
			)
			(
				(and
					(temp0 canScrollDown:)
					(> temp2 (- (temp0 nScreenSizeY?) global206))
				)
				(if gOFileReadWord_7
					(temp0
						scroll: 0 (- temp2 (- (temp0 nScreenSizeY?) global206))
					)
				else
					(temp0 scroll: 0 global110)
				)
			)
		)
	)
)

(class Ego of Actor
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
		cycleSpeed 7
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		code 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 7
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		robot 0
		facer 0
		edgeHit 0
		bAutoScroll 1
		bIsInitialized 0
		oWalkHandler 0
	)
	
	(method (init)
		(super init: &rest)
		(= signal (| signal $2000))
		(if (not cycler) (self setCycle: Walk))
		(= bIsInitialized (= bAutoScroll 1))
	)
	
	(method (doit)
		(super doit:)
		(if bAutoScroll (proc64988_0 self))
	)
	
	(method (dispose)
		(= bIsInitialized 0)
		(if oWalkHandler
			(oWalkHandler dispose:)
			(= oWalkHandler 0)
		)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp theEventMessage eventType eventMessage)
		(if (not bIsInitialized) (return 0))
		(= eventType (event type?))
		(= eventMessage (event message?))
		(cond 
			((and script (script handleEvent: event)) 1)
			((not (user canControl:)))
			((& eventType direction)
				(if
					(and
						(== (= theEventMessage eventMessage) 0)
						(& eventType keyDown)
					)
					(return (event claimed?))
				)
				(if
					(and
						(& eventType keyDown)
						(== theEventMessage (user prevDir?))
						mover
					)
					(= theEventMessage 0)
				)
				(user prevDir: theEventMessage)
				(self setDirection: theEventMessage)
				(event claimed: 1)
			)
			((& eventType $4000)
				(if (& eventType $1000)
					(if oWalkHandler
						(oWalkHandler doit: (event x?) (+ (event y?) z))
						(event claimed: 1)
						(return 1)
					)
					(switch useObstacles
						(0
							(self setMotion: MoveTo (event x?) (+ (event y?) z))
						)
						(1
							(self walkTo: (event x?) (+ (event y?) z))
						)
						(2
							(self walkTo: (event x?) (+ (event y?) z))
						)
					)
					(user prevDir: 0)
					(event claimed: 1)
				else
					(super handleEvent: event)
				)
			)
			(else (super handleEvent: event))
		)
		(return (event claimed?))
	)
	
	(method (facingMe)
		(return 1)
	)
	
	(method (setMotion mType)
		(if mType
			(= bAutoScroll 1)
			(if
			(and curRoom ((curRoom plane?) isKindOf: ScrollPlane))
				((curRoom plane?) killPan:)
			)
		)
		(super setMotion: mType &rest)
	)
	
	(method (get what &tmp temp0 theWhat)
		(= temp0 0)
		(while (< temp0 argc)
			((= theWhat [what temp0]) moveTo: -3)
			(++ temp0)
		)
	)
	
	(method (put what recipient)
		(if (self has: what)
			(what moveTo: (if (== argc 1) -1 else recipient))
		)
	)
	
	(method (has what)
		(if (or (what ownedBy: -3) (what ownedBy: -2))
		else
			(what ownedBy: -4)
		)
	)
	
	(method (setWalkHandler theOWalkHandler)
		(if oWalkHandler
			(oWalkHandler dispose:)
			(= oWalkHandler 0)
		)
		(= oWalkHandler theOWalkHandler)
	)
	
	(method (walkTo)
	)
)
