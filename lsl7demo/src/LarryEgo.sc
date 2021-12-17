;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64007)
(include sci.sh)
(use Main)
(use PopMenu)
(use GenDialog)
(use PolyPath)
(use CueObj)
(use StopWalk)
(use Grooper)
(use Ego)
(use Motion)
(use Actor)
(use System)

(public
	ego 0
)

(local
	local0
	local1
	local2
	local3
	local4 =  1
	local5 =  1
	local6 =  1
)
(procedure (localproc_0114 param1)
	(if
		(or
			(not argc)
			(not param1)
			(not (param1 isKindOf: Actor))
		)
		(PrintDebug {Bad call of GetActorLoop. L7Ego.sc. djm})
		(return 0)
	)
	(return
		(if (< (param1 loop?) 8)
			(return (param1 loop?))
		else
			(return (param1 cel?))
		)
	)
)

(instance poNull of Prop
	(properties)
)

(instance soWalkScript of Script
	(properties)
	
	(method (changeState newState &tmp theCaller)
		(switch (= state newState)
			(0
				(if local4
					(= local2 1)
					(ego
						setHeading: (GetAngle (ego x?) (ego y?) local0 local1) self
					)
				else
					(self cue:)
				)
			)
			(1
				(= local2 2)
				(= local3 (localproc_0114 ego))
				(if local4
					(ego view: -5421 loop: local3 cel: 0 setCycle: End self)
					(UpdateScreenItem ego)
				else
					(self cue:)
				)
			)
			(2
				(= local2 3)
				(ego
					view: -5436
					loop: local3
					cel: 0
					setCycle: StopWalk -1
				)
				(if local6
					(ego setMotion: PolyPath local0 local1 self)
				else
					(ego setMotion: MoveTo local0 local1 self)
				)
				(UpdateScreenItem ego)
			)
			(3
				(= local3 (localproc_0114 ego))
				(= local2 4)
				(if local5
					(ego view: -5416 loop: local3 cel: 0 setCycle: End self)
					(UpdateScreenItem ego)
				else
					(self cue:)
				)
			)
			(4
				(= local2 0)
				(ego
					view: -5436
					loop: 8
					cel: local3
					setCycle: StopWalk -1
				)
				(UpdateScreenItem ego)
				(if (and caller (== newRoomNum curRoomNum))
					(= theCaller caller)
					(= caller 0)
					(self dispose:)
					(theCaller cue: register)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance oWalkFeature of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= nsTop (= nsLeft 0))
		(= nsRight (plane getWidth:))
		(= nsBottom (plane getHeight:))
		(self myPriority: -1)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(= plane 0)
	)
	
	(method (handleEvent event)
		(if
			(and
				(user canControl:)
				(self onMe: event)
				(& (event type?) evMOUSEBUTTON)
				ego
				(ego plane?)
				(ego isNotHidden:)
				((ego plane?) isEnabled:)
			)
			(event localize: (ego plane?))
			(event type: 20480)
			(ego setScript: 0)
			(proc64896_12 ego)
			(ego handleEvent: event)
			(event claimed: 0)
		)
		(return 0)
	)
)

(class LarryEgo of Ego
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
		oMyVerbHandlers 0
		oCantBeHereHandler 0
	)
	
	(method (dispose)
		(if oMyVerbHandlers
			(oMyVerbHandlers dispose:)
			(= oMyVerbHandlers 0)
		)
		(if oCantBeHereHandler
			(oCantBeHereHandler dispose:)
			(= oCantBeHereHandler 0)
		)
		(super dispose: &rest)
	)
	
	(method (cantBeHere)
		(if oCantBeHereHandler
			(oCantBeHereHandler doit:)
		else
			(super cantBeHere:)
		)
	)
	
	(method (addVerbHandler param1)
		(if (not oMyVerbHandlers)
			(= oMyVerbHandlers (Set new:))
		)
		(oMyVerbHandlers addToFront: param1)
	)
	
	(method (deleteVerbHandler param1)
		(if oMyVerbHandlers (oMyVerbHandlers delete: param1))
	)
	
	(method (checkVerbHandlers &tmp oMyVerbHandlersFirst temp1)
		(if oMyVerbHandlers
			(= oMyVerbHandlersFirst (oMyVerbHandlers first:))
			(while oMyVerbHandlersFirst
				(if
					(and
						(= temp1 (List 8 oMyVerbHandlersFirst))
						(temp1 doVerb: &rest)
					)
					(return 1)
				)
				(= oMyVerbHandlersFirst
					(oMyVerbHandlers next: oMyVerbHandlersFirst)
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

(instance ego of LarryEgo
	(properties
		noun 1
		modNum 60
		view -5436
	)
	
	(method (init)
		(super init: &rest)
		(oWalkFeature init: &rest)
		(proc64038_1 self)
		(self addHotspotVerb: 83)
	)
	
	(method (dispose)
		(if (oWalkFeature plane?) (oWalkFeature dispose:))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(if (not (self checkVerbHandlers: theVerb))
			(switch theVerb
				(1
					(messager say: noun theVerb 0 0 0 modNum)
				)
				(83
					(messager say: noun theVerb 0 0 0 modNum)
				)
			)
		)
	)
	
	(method (getName)
		(MakeMessageText noun 68 0 1 modNum)
	)
	
	(method (normalize param1 param2 param3)
		(if argc (self loop: param1) else (self loop: 2))
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
		(if (not (if (> argc 1) param2))
			(self
				oldScaleX: 128
				scaleX: 128
				scaleY: 128
				setScale: 0
				setScaler: 0
				setStep: 11 5
			)
		)
		(self
			view: (if (> argc 2) param3 else -5436)
			z: 0
			setMotion: 0
			setLooper: Grooper
			setLoop: -1
			setPri: -1
			illegalBits: 0
			setCycle: StopWalk -1
			setSpeed: (theGame nGameSpeed?)
			state: (= state (| state $0002))
			edgeHit: 0
			ignoreActors: 1
		)
	)
	
	(method (walkTo param1 param2 param3 param4 param5 param6 &tmp temp0)
		(if (< argc 3) (= temp0 0) else (= temp0 param3))
		(if (< argc 4) (= local4 1) else (= local4 param4))
		(if (< argc 5) (= local5 1) else (= local5 param5))
		(if (< argc 6) (= local6 1) else (= local6 param6))
		(= local0 param1)
		(= local1 param2)
		(ego setMotion: 0)
		(if
		(and (== local0 (ego x?)) (== local1 (ego y?)))
			(if temp0
				(if (not cuees) (= cuees (Set new:)))
				(cuees add: ((Cue new:) cuee: temp0 yourself:))
			)
			(return)
		)
		(ego normalize: (localproc_0114 ego) 1)
		(proc64896_12 soWalkScript)
		(soWalkScript caller: 0)
		(soWalkScript client: 0)
		(poNull setScript: soWalkScript temp0)
	)
)
