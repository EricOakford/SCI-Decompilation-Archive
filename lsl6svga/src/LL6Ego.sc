;;; Sierra Script 1.0 - (do not remove this comment)
(script# 90)
(include sci.sh)
(use Main)
(use fileScr)
(use Print)
(use StopWalk)
(use Timer)
(use Grooper)
(use Ego)
(use Sound)
(use System)

(public
	ego 0
)

(class ego of Ego
	(properties
		scratch 0
		heading 0
		noun 2
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
		view 900
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
		edgeHit 0
	)
	
	(method (doit)
		(super doit:)
		(if (>= y 138) (= edgeHit 3))
		(if (<= global178 0)
			(= global178 (Random 30 120))
			(scratchTimer setTicks: 60 scratchTimer)
		)
		(if
		(and (== global178 1) (not (OneOf curRoomNum 350 280)))
			(= global178 0)
			(if (not (proc79_14))
				(theGame handsOff:)
				(self setScript: (ScriptID 97 0) 0 0)
			)
		)
	)
	
	(method (dispose)
		(scratchTimer dispose: delete:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(messager
						say: noun 4 0 (if (< global168 5) (++ global168) else global168) 0 0
					)
				)
				(59
					(if (Btst 254)
						(messager say: 2 59 1 1 0 0)
						(return 1)
					else
						(messager say: 2 59 0 1 0 0)
						(return 1)
					)
				)
				(9
					(ego put: 3)
					(theGame changeScore: -5 159)
					(if (not (self script?)) (self setScript: beerScript))
					(messager say: 2 9 0 1 0 0)
					(return 1)
				)
				(2
					(messager say: noun theVerb 0 (Random 1 4) 0 0)
					(return 1)
				)
				(49
					(if (== curRoomNum 240)
						(messager say: 2 49 22 0 0 0)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(63
					(cond 
						(
							(and
								(not (OneOf curRoomNum 350 280))
								(not (proc79_14))
							)
							(self setScript: (ScriptID 79 13))
						)
						((Message 0 0 noun theVerb 0 1) (messager say: noun theVerb 0 0 0 0))
						(else (messager say: noun 0 0 0 0 0))
					)
				)
				(6
					(if
						(and
							(not (proc79_14))
							(not (OneOf curRoomNum 350 280))
						)
						(= global178 0)
						(theGame handsOff:)
						(self setScript: (ScriptID 97 0) 0 1)
					else
						(messager say: 2 6 0 0 0 0)
					)
				)
				(else 
					(if (Message 0 0 noun theVerb 0 1)
						(messager say: noun theVerb 0 0 0 0)
					else
						(messager say: noun 0 0 0 0 0)
					)
				)
			)
		)
	)
	
	(method (get what &tmp temp0)
		(= temp0 0)
		(while (< temp0 argc)
			((inventory at: [what temp0]) moveTo: -2)
			(++ temp0)
		)
		(if (& (inventory state?) $0020)
			(inventory setCurIndexToItem: (inventory at: what))
		)
	)
	
	(method (put what recipient &tmp temp0 temp1)
		(if (self has: what)
			((= temp0 (inventory at: what))
				moveTo: (if (== argc 1) -1 else recipient)
			)
			(DeleteScreenItem (= temp1 (inventory at: what)))
			(temp1 signal: (| (temp1 signal?) $0004))
			(if
				(and
					(== (theIconBar curIcon?) temp0)
					(not (theGame isHandsOff?))
				)
				(theIconBar curIcon: (ScriptID 0 3))
				(if
					(or
						(not (theIconBar contains: (ScriptID 0 3)))
						(& ((ScriptID 0 3) state?) $0004)
					)
					(theIconBar advanceCurIcon:)
				else
					(theGame setCursor: ((ScriptID 0 3) getCursor:))
				)
			)
			(if (== (inventory curIcon?) temp0)
				(inventory curIcon: 0)
			)
		)
		(if (& (inventory state?) $0020) (inventory show:))
	)
	
	(method (has what &tmp temp0)
		(if (= temp0 (inventory at: what)) (temp0 ownedBy: -2))
	)
	
	(method (normalize param1 param2 param3)
		(if (> argc 1) (ego loop: param2))
		(if (not (if (> argc 2) param3))
			(ego scaleX: 128 scaleY: 128 setScale: 0 setScaler: 0)
		)
		(if (and global100 (== param1 0))
			(Prints {Ego's view is 0, you FOK!})
		)
		(ego
			view: (if argc param1 else (self view?))
			signal: 4129
			z: 0
			setLooper: egoGrooper
			setLoop: -1
			setPri: -1
			setMotion: 0
			illegalBits: 0
			setStep: 3 2
			ignoreActors:
				(not
					(OneOf curRoomNum 680 640 305 300 200 500 505 660 690 820)
				)
			setCycle: StopWalk -1
			setSpeed: gGEgoCycleSpeed_2
			state: (= state (| state $0002))
		)
	)
)

(instance egoGrooper of Grooper
	(properties)
	
	(method (doit param1 param2 param3 param4)
		(if (OneOf (ego view?) 900 800)
			(super
				doit:
					param1
					param2
					(if (> argc 2) param3 else 0)
					(if (> argc 3) param4 else 0)
					&rest
			)
		else
			(super
				doit: param1 param2 (if (> argc 2) param3 else 0) 1
			)
		)
	)
)

(instance scratchTimer of Timer
	(properties)
	
	(method (cue)
		(-- global178)
		(scratchTimer setTicks: 60 self)
	)
)

(instance beerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sFx number: 517 loop: 1 play:)
				(= ticks 90)
			)
			(1 (sFx stop:) (self dispose:))
		)
	)
)

(instance sFx of Sound
	(properties)
)
