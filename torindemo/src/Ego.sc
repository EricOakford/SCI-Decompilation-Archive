;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64988)
(include game.sh)
(use Main)
(use ScrollView)
(use PolyPath)
(use Motion)
(use Actor)


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
		signal $0021
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
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		robot 0
		nYTarget 0
		edgeHit 0
		bMouseDown 1
		oCast 0
		vCheck 0
	)
	
	(method (init)
		(super init: &rest)
		(= signal (| signal $2000))
		(if (not cycler) (self setCycle: Walk))
		(= oCast (= bMouseDown 1))
	)
	
	(method (doit &tmp temp0 temp1 temp2)
		(super doit:)
		(if (and bMouseDown (plane isKindOf: ScrollPlane))
			(= temp0 (plane nModule: ego))
			(= temp1 (plane nNoun: ego))
			(= temp2
				(/ (* (CelHigh (ego view?) 0 0) (ego scaleY?)) 128)
			)
			(cond 
				((< temp0 global205)
					(if global108
						(plane nInitCursorX: (- temp0 global205) 0)
					else
						(plane nInitCursorX: (- 0 global109) 0)
					)
				)
				((> temp0 (- (plane saveTime?) global205))
					(if global108
						(plane
							nInitCursorX: (- temp0 (- (plane saveTime?) global205)) 0
						)
					else
						(plane nInitCursorX: global109 0)
					)
				)
			)
			(= global110
				(- (thePlane doDouble:) (+ (* 2 global206) temp2))
			)
			(cond 
				((< (- temp1 temp2) global206)
					(if global108
						(plane nInitCursorX: 0 (- (- temp1 temp2) global206))
					else
						(plane nInitCursorX: 0 (- 0 global110))
					)
				)
				((> temp1 (- (plane oSubtitle?) global206))
					(if global108
						(plane
							nInitCursorX: 0 (- temp1 (- (plane oSubtitle?) global206))
						)
					else
						(plane nInitCursorX: 0 global110)
					)
				)
			)
		)
	)
	
	(method (dispose)
		(= oCast 0)
		(if vCheck (vCheck dispose:) (= vCheck 0))
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp theEventMessage eventType eventMessage)
		(if (not oCast) (return 0))
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
			((& eventType userEvent)
				(if (& eventType walkEvent)
					(if vCheck
						(vCheck doit: (event x?) (+ (event y?) z))
						(event claimed: 1)
						(return 1)
					)
					(switch useObstacles
						(0
							(self setMotion: MoveTo (event x?) (+ (event y?) z))
						)
						(1
							(self setMotion: PolyPath (event x?) (+ (event y?) z))
						)
						(2
							(self
								setMotion: PolyPath (event x?) (+ (event y?) z) 0 0
							)
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
			(= bMouseDown 1)
			(if
			(and curRoom ((curRoom plane?) isKindOf: ScrollPlane))
				((curRoom plane?) bSpinning:)
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
	
	(method (put what recipient &tmp theWhat)
		(if (self has: what)
			((= theWhat what)
				moveTo: (if (== argc 1) -1 else recipient)
			)
			(if
			(and theIconBar (== (theIconBar curInvIcon?) theWhat))
				(theIconBar
					curInvIcon: 0
					disableIcon:
						((theIconBar useIconItem?)
							setCursor: normalCursor
							yourself:
						)
				)
			)
		)
	)
	
	(method (has what)
		(if (what ownedBy: -3) else (what ownedBy: -2))
	)
	
	(method (lCheck theVCheck)
		(if vCheck (vCheck dispose:) (= vCheck 0))
		(= vCheck theVCheck)
	)
)
