;;; Sierra Script 1.0 - (do not remove this comment)
(script# rgOcean)
(include game.sh)
(use Main)
(use Intrface)
(use EgoDead)
(use ForCount)
(use Chase)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	oceanRg 0
)

(instance oceanRg of Region
	
	(method (init)
		(super init: &rest)
		(= initialized FALSE)
		(Load VIEW 517)
		(if (not script) (self setScript: dieScript))
		(seaGull init:)
		(ego setStep: 1 1 setCycle: treadWaterCycle)
	)
	
	(method (dispose)
		(if (script state?) (HandsOn))
		(ego setStep: 3 2)
		(super dispose:)
	)
	
	(method (newRoom roomNum)
		(= keep (OneOf roomNum 8 15 16))
		(super newRoom: roomNum)
	)
)

(instance dieScript of Script
	(properties
		seconds 60
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(switch (ego loop?)
					(loopE
						(ego setMotion: MoveTo 310 (ego y?))
					)
					(loopW
						(ego setMotion: MoveTo 10 (ego y?))
					)
					(loopN
						(ego setMotion: MoveTo (ego x?) 10)
					)
					(loopS
						(ego setMotion: MoveTo (ego x?) 180)
					)
				)
				(= cycles 25)
			)
			(2
				(ego
					view: 617
					setLoop: 1
					setMotion: 0
					setCycle: ForwardCounter 3 self
				)
			)
			(3
				(ego setLoop: 0 setCycle: EndLoop self)
			)
			(4
				(ego dispose:)
				(= cycles 5)
			)
			(5
				(EgoDead 907 2 0 308 0)
			)
		)
	)
)

(instance treadWaterCycle of Forward
	
	(method (doit)
		(if (client isStopped:)
			(client view: 517)
		else
			(client view: 217)
		)
		(super doit: &rest)
	)
)

(instance seaGull of Actor
	(properties
		y -20
		x -20
		view 15
	)
	
	(method (init)
		(super init:)
		(self
			setStep: 6 4
			setCycle: Walk
			illegalBits: 0
			ignoreHorizon: TRUE
			ignoreActors: TRUE
			setPri: 15
			setScript: seaGullFliesScript
		)
	)
	
	(method (dispose)
		(self posn: -20 0)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/bird,gull]>')
				(cond 
					((Said 'look[<at]')
						(Print 308 1)
					)
					((Said 'kill')
						(Print 308 2)
					)
					((Said 'catch')
						(Print 308 3)
					)
				)
			)
		)
	)
)

(instance seaGullFliesScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 10)))
			(1
				(self
					setScript:
						(if (Random 0 1)
							seaGullFliesEastScript
						else
							seaGullFliesWestScript
						)
						self
				)
			)
			(2
				(self init:)
			)
		)
	)
)

(instance seaGullFliesEastScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(seaGull
					setLoop: 0
					posn: -20 72
					setMotion: MoveTo 168 89 self
				)
			)
			(1
				(if
					(and
						(> 280 (ego x?))
						(> (ego x?) (+ (seaGull x?) 20))
						1
					)
					(self setScript: peckEastScript self)
				else
					(seaGull setMotion: MoveTo 340 72 self)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance seaGullFliesWestScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(seaGull
					setLoop: 1
					posn: 340 72
					setMotion: MoveTo 168 89 self
				)
			)
			(1
				(if
					(and
						(< 40 (ego x?))
						(< (ego x?) (- (seaGull x?) 30))
						1
					)
					(self setScript: peckWestScript self)
				else
					(seaGull setMotion: MoveTo -20 72 self)
				)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance peckWestScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(seaGull setMotion: Chase ego 30 self)
			)
			(1
				(User canControl: FALSE canInput: FALSE)
				(seaGull
					setMotion: MoveTo (+ (ego x?) 10) (- (ego y?) 10) self
				)
			)
			(2
				(= register (ego mover?))
				(ego mover: 0 view: 617 setLoop: 1 setCycle: Forward)
				(seaGull setLoop: 3 setCycle: ForwardCounter 5 self)
			)
			(3
				(HandsOn)
				(ego
					view: 217
					setLoop: -1
					setMotion: register
					setCycle: treadWaterCycle
				)
				(seaGull
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo -20 72 self
				)
			)
			(4 (self dispose:))
		)
	)
)

(instance peckEastScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(seaGull setMotion: Chase ego 30 self)
			)
			(1
				(User canControl: FALSE canInput: FALSE)
				(seaGull
					setMotion: MoveTo (- (ego x?) 10) (- (ego y?) 10) self
				)
			)
			(2
				(= register (ego mover?))
				(ego mover: 0 view: 617 setLoop: 1 setCycle: Forward)
				(seaGull setLoop: 2 setCycle: ForwardCounter 5 self)
			)
			(3
				(HandsOn)
				(ego
					view: 217
					setLoop: -1
					setMotion: register
					setCycle: treadWaterCycle
				)
				(seaGull
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 340 72 self
				)
			)
			(4 (self dispose:))
		)
	)
)
