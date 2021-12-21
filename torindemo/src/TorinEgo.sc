;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64007)
(include sci.sh)
(use Main)
(use FRButton)
(use Set)
(use Script)
(use Print)
(use Feature)
(use StopWalk)
(use Grooper)
(use Ego)
(use Motion)
(use Actor)
(use System)

(public
	oTPEgo 0
	soEgoGetLow 1
	soEgoGetMed 2
	soEgoGetHigh 3
)

(local
	theSel_1205
)
(instance oWalkFeature of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= nsTop (= nsLeft 0))
		(= nsRight (plane findData:))
		(= nsBottom (plane doDouble:))
		(self nScrollMaxX: -1)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(= plane 0)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== gVerb 1)
				(self onMe: event)
				(& (event type?) evMOUSEBUTTON)
				ego
				(ego plane?)
				((ego plane?) nScreenOrgY:)
			)
			(event localize: (ego plane?))
			(event type: 20480)
			(ego handleEvent: event)
			(event claimed: 0)
		)
		(return 0)
	)
)

(class TorinEgo of Ego
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
		updateScrollbar 0
		sayNoSave 0
	)
	
	(method (dispose)
		(if updateScrollbar
			(updateScrollbar dispose:)
			(= updateScrollbar 0)
		)
		(if sayNoSave (sayNoSave dispose:) (= sayNoSave 0))
		(super dispose: &rest)
	)
	
	(method (cantBeHere)
		(if sayNoSave
			(sayNoSave doit:)
		else
			(super cantBeHere:)
		)
	)
	
	(method (oFlagValues param1)
		(if (not updateScrollbar)
			(= updateScrollbar (Set new:))
		)
		(updateScrollbar addToFront: param1)
	)
	
	(method (oPointsLost param1)
		(if updateScrollbar (updateScrollbar delete: param1))
	)
	
	(method (oScoreFlags &tmp updateScrollbarFirst temp1)
		(if updateScrollbar
			(= updateScrollbarFirst (updateScrollbar first:))
			(while updateScrollbarFirst
				(if
					(and
						(= temp1 (KList 8 updateScrollbarFirst))
						(temp1 doVerb: &rest)
					)
					(return 1)
				)
				(= updateScrollbarFirst
					(updateScrollbar next: updateScrollbarFirst)
				)
			)
		)
		(return 0)
	)
)

(class VerbHandler of Obj
	(properties
		scratch 0
	)
	
	(method (doVerb)
	)
)

(instance oTPEgo of TorinEgo
	(properties
		view -5436
	)
	
	(method (init)
		(super init: &rest)
		(LOOKUP_ERROR init: (ScriptID 0 1))
		(if (== curRoomNum 20800) (self setVisibleRange: 13))
	)
	
	(method (dispose)
		(if (LOOKUP_ERROR plane?) (LOOKUP_ERROR dispose:))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(if (self oScoreFlags: theVerb) (return))
		(switch theVerb
			(13 (Prints LOOKUP_ERROR))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (oPanner param1 param2 param3)
		(if (== argc 3)
			(self loop: param3)
		else
			(self loop: 2)
		)
		(= heading
			(switch loop
				(0 90)
				(1 270)
				(2 180)
				(3 0)
				(4 135)
				(5 225)
				(6 45)
				(7 315)
			)
		)
		(if (not (if argc param1))
			(self
				oldScaleX: 128
				scaleX: 128
				scaleY: 128
				setScale: 0
				setScaler: 0
			)
		)
		(self
			view: (if (> argc 1) param2 else -5436)
			z: 0
			setMotion: 0
			setLooper: Grooper
			setLoop: -1
			setPri: -1
			illegalBits: 0
			setStep: 12 5
			setCycle: StopWalk -1
			setSpeed: (theGame oCantBeHereHandler?)
			state: (= state (| state $0002))
			edgeHit: 0
			ignoreActors: 1
		)
	)
	
	(method (get what)
		(if (== (what nLevel?) (ScriptID 64001 1))
			(proc64002_8)
		else
			(proc64002_7)
		)
		(super get: what &rest)
	)
)

(class NullLooper of Code
	(properties
		scratch 0
	)
	
	(method (doit)
	)
	
	(method (cue)
	)
)

(class WalkBehindHill of Script
	(properties
		scratch 0
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
		setNoScore 0
		unSet 0
		priority 0
		lastNoun 0
		heading 0
		lastVerb 0
		lastCase 0
		lastMod 0
	)
	
	(method (init)
		(if (not lastVerb) (= lastVerb ego))
		(if (not setNoScore) (= setNoScore (lastVerb x?)))
		(if (not unSet) (= unSet (+ (lastVerb y?) 60)))
		(super init: &rest)
	)
	
	(method (dispose)
		(if lastMod (lastMod dispose:) (= lastMod 0))
		(if lastCase (lastCase dispose:) (= lastCase 0))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(lastVerb setPri: priority)
				(= lastMod (lastVerb scaler?))
				(lastVerb scaler: 0)
				(lastVerb setHeading: heading self)
			)
			(1 (= cycles 1))
			(2
				(= lastCase (lastVerb looper?))
				(lastVerb looper: NullLooper)
				(lastVerb setMotion: MoveTo setNoScore unSet self)
			)
			(3
				(lastVerb looper: lastCase)
				(lastVerb scaler: lastMod)
				(= lastMod (= lastCase 0))
				(if lastNoun
					(= theSel_1205 lastNoun)
					(self dispose:)
					(theSel_1205 cue:)
					(return)
				)
				(self dispose:)
			)
		)
	)
)

(class SoEgoGet of Script
	(properties
		scratch 0
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
		frontX 0
		backX -5435
	)
	
	(method (dispose)
		(ego show:)
		(if frontX (frontX dispose:))
		(super dispose:)
	)
	
	(method (changeState newState &tmp egoCel temp1)
		(switch (= state newState)
			(0
				(ego scrollTo:)
				(= egoCel (ego cel:))
				(= frontX
					((Prop new:)
						view: backX
						loop: egoCel
						cel: 0
						posn: (ego x?) (ego y?)
						setPri: (ego priority?)
						yourself:
					)
				)
				(if (& (ego scaleSignal?) $0001)
					(frontX
						setScale:
						scaleX: (ego scaleX?)
						scaleY: (ego scaleY?)
					)
				)
				(= temp1 (- (/ (+ (frontX lastCel:) 1) 2) 1))
				(ego hide:)
				(frontX init: setCycle: CT temp1 1 self)
			)
			(1
				(if register (register cue:) (= register 0))
				(frontX setCycle: End self)
			)
			(2 (self dispose:))
		)
	)
)

(instance soEgoGetHigh of SoEgoGet
	(properties)
)

(instance soEgoGetMed of SoEgoGet
	(properties
		backX -5434
	)
)

(instance soEgoGetLow of SoEgoGet
	(properties
		backX -5433
	)
)
