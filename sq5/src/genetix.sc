;;; Sierra Script 1.0 - (do not remove this comment)
(script# rgGenetix)
(include game.sh)
(use Main)
(use VelocityMover)
(use Scaler)
(use Feature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	genetix 0
	NormalFlyEgo 1
	proc31_2 2
	sGenInNout 3
)

(local
	[local0 11] = [354 259 6 126 -20 -20 16 90 281 280 99]
	[local11 11] = [120 103 123 104 128 150 100 53 126 43 99]
	[local22 11] = [760 740 790 730 760 730 740 790 730 730 99]
)
(procedure (NormalFlyEgo theX theY)
	(ego
		view: 600
		init:
		signal: (| skipCheck ignrHrz startUpdOn)
		posn: theX theY
		moveSpeed: 0
		ignoreActors: TRUE
		illegalBits: 0
		setLoop: 1
		setPri: 15
		cycleSpeed: 2
		setStep: 7 7
		setCycle: Forward
		setMotion: VelocityMover theX theY 0 0
		looper: 0
	)
	(switch (curRoom picture?)
		(116
			(ego
				scaleSignal: (| (ego scaleSignal?) noStepScale)
				setScale: Scaler 64 17 139 24
			)
		)
		(112
			(ego
				scaleSignal: (| (ego scaleSignal?) noStepScale)
				setScale: Scaler 64 17 139 24
				setStep: 8 8
			)
		)
		(110
			(ego
				scaleSignal: (| (ego scaleSignal?) noStepScale)
				setScale: Scaler 64 17 139 24
				setStep: 3 3
				moveSpeed: 1
			)
		)
		(113
			(ego
				scaleSignal: (| (ego scaleSignal?) noStepScale)
				setScale: Scaler 64 17 139 24
				setStep: 3 3
				moveSpeed: 0
			)
		)
	)
)

(procedure (proc31_2 param1 &tmp temp0 temp1 temp2)
	(= temp1
		(switch
			(= temp0
				(cond 
					((< 150 param1) 120)
					((< 100 param1) 33)
					((< 59 param1) 32)
					(else 31)
				)
			)
			(31 17)
			(32 17)
			(33 17)
			(120 70)
		)
	)
	(= temp2
		(switch temp0
			(31
				(if (OneOf (curRoom picture?) 112 116) 9 else 13)
			)
			(32
				(if (OneOf (curRoom picture?) 112 116) 9 else 13)
			)
			(33 13)
			(120 15)
		)
	)
	(ego setScale: Scaler temp0 temp1 167 80 setPri: temp2)
)

(class genetix of Region
	
	(method (dispose)
		(theMusic1 stop:)
		(theMusic2 stop:)
		(super dispose: &rest)
	)
	
	(method (newRoom n)
		(= keep (OneOf n 730 740 750 760 770 790))
		(= initialized 0)
		(super newRoom: n)
	)
)

(instance sGenInNout of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setMotion: MoveTo [local0 register] [local11 (theGame handsOff:)] self
				)
			)
			(1
				(if (and (Btst fEgoHumanAgain) (OneOf (curRoom curPic?) 110 112 113))
					(= seconds 2)
				else
					(= cycles 2)
				)
			)
			(2
				(if (== curRoom [local22 register])
					(theGame handsOn:)
					(self dispose:)
				else
					(theGame handsOn:)
					(curRoom newRoom: [local22 register])
				)
			)
		)
	)
)

(class MyFeature of Feature
	
	(method (init &tmp type)
		(= type onMeCheck)
		(super init:)
		(self setOnMeCheck: 1 type)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((not (Btst fEgoIsFly))
					(super handleEvent: event &rest)
				)
				(
					(and
						(& (event type?) userEvent)
						(self onMe: event)
						(self isNotHidden:)
						(Btst fEgoIsFly)
					)
					(event claimed: TRUE)
					(self doVerb: (event message?))
					(return (event claimed?))
				)
			)
		)
	)
)

(class MyProp of Prop
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((not (Btst fEgoIsFly))
					(super handleEvent: event &rest)
				)
				(
					(and
						(& (event type?) userEvent)
						(self onMe: event)
						(self isNotHidden:)
						(Btst fEgoIsFly)
					)
					(event claimed: TRUE)
					(self doVerb: (event message?))
					(return (event claimed?))
				)
			)
		)
	)
)

(class MyActor of Actor
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((not (Btst fEgoIsFly))
					(super handleEvent: event &rest)
				)
				(
					(and
						(& (event type?) userEvent)
						(self onMe: event)
						(self isNotHidden:)
						(Btst fEgoIsFly)
					)
					(event claimed: TRUE)
					(self doVerb: (event message?))
					(return (event claimed?))
				)
			)
		)
	)
)

(instance cliffy of MyActor
	(properties
		noun 1
		view 20
		signal ignrAct
	)
)
