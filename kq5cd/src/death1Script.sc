;;; Sierra Script 1.0 - (do not remove this comment)
(script# 604)
(include sci.sh)
(use Main)
(use CodeCue)
(use Motion)
(use Actor)
(use System)

(public
	death1Script 0
	death2Script 1
	death3Script 2
	death4Script 3
	cedric 5
	tigerStuffScript 6
	summonWandScript 7
)

(local
	[local0 9] = [1000 233 21 4 11 24 19 23 30]
)
(instance death1Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 896 loop: 1 play:)
				(= cycles 10)
			)
			(1
				(gMordObj
					setMotion: MoveTo (- (ego x?) 8) (- (ego y?) 13) self
				)
			)
			(2
				(ego dispose:)
				(= seconds 5)
			)
			(3
				(= global103 0)
				(= deathMessage 788)
				(EgoDead)
			)
		)
	)
)

(instance death2Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 896 loop: 1 play:)
				(gNewActorCloner_3 setCycle: End self)
				(switch global387
					(4
						(gNewActorCloner dispose:)
						(gNewActorCloner_2 dispose:)
						(ego view: 722 setLoop: 11 cel: 0 setCycle: CT 1 1)
					)
				)
			)
			(1
				(gNewActorCloner_5
					view: 728
					setLoop: 6
					illegalBits: 0
					ignoreActors:
					posn: (+ (gNewActorCloner_3 x?) 64) (gNewActorCloner_3 y?)
					setStep: 6 6
					setPri: 13
					init:
				)
				(RedrawCast)
				(gNewActorCloner_5
					setMotion:
						MoveTo
						(+ (gNewActorCloner_5 x?) 40)
						(+ (gNewActorCloner_5 y?) 40)
						self
				)
				(theAudio number: 8200 loop: 1 play:)
				(gNewActorCloner_3 setCycle: Beg)
			)
			(2
				(if (!= global387 4)
					(gNewActorCloner dispose:)
					(gNewActorCloner_2 dispose:)
				)
				(gNewActorCloner_5 dispose:)
				(switch global387
					(4 (ego setCycle: End self))
					(2
						(ego view: 719 setLoop: 2 cel: 0 setCycle: End self)
					)
					(1
						(ego view: 719 setLoop: 0 cel: 0 setCycle: End self)
					)
					(3
						(ego view: 719 setLoop: 3 z: 0 cel: 0 setCycle: End self)
					)
				)
			)
			(3
				(if (== global387 4)
					(ego view: 728 setLoop: 7 cel: 0 setCycle: End self)
				else
					(= cycles 1)
				)
			)
			(4
				(= global103 0)
				(= deathMessage 788)
				(EgoDead)
			)
		)
	)
)

(instance death3Script of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theMusic number: 896 loop: 1 play:)
				(gMordObj setLoop: 12 cycleSpeed: 2 setCycle: End self)
			)
			(1
				(gMordObj setCycle: Beg)
				(switch global387
					(4 (= temp0 5))
					(2 (= temp0 6))
					(3
						(gNewActorCloner dispose:)
						(= temp0 4)
					)
				)
				(ego
					view: 719
					setLoop: temp0
					cycleSpeed: 2
					cel: 0
					z: 0
					setCycle: End self
				)
			)
			(2 (= seconds 2))
			(3
				(= global103 0)
				(= deathMessage 788)
				(EgoDead)
			)
		)
	)
)

(instance death4Script of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theMusic number: 896 loop: 1 play:)
				(gMordObj setLoop: 5)
				(gNewActorCloner_2 setLoop: 6)
				(= seconds 1)
			)
			(1
				(gMordObj setLoop: 7)
				(gNewActorCloner_2 setLoop: 8)
				(= seconds 1)
			)
			(2
				(gMordObj dispose:)
				(gNewActorCloner_2 dispose:)
				(switch global387
					(4 (= temp0 7))
					(2 (= temp0 2))
					(1 (= temp0 0))
				)
				(ego
					view: 719
					setLoop: temp0
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(3
				(ego view: 719 setLoop: 1 cel: 0 setCycle: End self)
			)
			(4 (= seconds 2))
			(5
				(= global103 0)
				(= deathMessage 788)
				(EgoDead)
			)
		)
	)
)

(instance cedric of Actor
	(properties
		x 346
		y 39
		view 720
		illegalBits $0000
	)
)

(instance tigerStuffScript of Script
	(properties)
	
	(method (changeState newState)
		(if (== (client script?) self)
			(switch (= state newState)
				(0
					(client setCycle: End self)
					(theAudio number: 7064 loop: 1 play:)
				)
				(1 (client setCycle: Beg self))
				(2 (= seconds (Random 1 5)))
				(3
					(if (> (DoAudio 6) -1) (-- state))
					(= cycles 1)
				)
				(4 (self init:))
			)
		)
	)
)

(instance summonWandScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gNewActorCloner
					view: 714
					setLoop: 4
					illegalBits: 0
					setPri: 3
					cycleSpeed: 1
					setStep: 8 8
					posn: 227 66 0
					init:
				)
				(gMordObj setLoop: 1 cel: 0 setCycle: End self)
				(theMusic2 number: 838 loop: 1 play:)
			)
			(1
				(gNewActorCloner
					setPri: 15
					setCycle: Fwd
					posn: 215 59
					moveSpeed: 1
					setMotion: (ScriptID 124 2) self
				)
				(ego put: 35)
			)
			(2
				(gNewActorCloner
					setMotion: MoveTo (+ (gMordObj x?) 16) (- (gMordObj y?) 31) self
				)
			)
			(3
				(gNewActorCloner view: 712 setPri: -1 z: 1000 setCycle: 0)
				(gMordObj setLoop: 2 cel: 0 setCycle: End self)
			)
			(4
				(if (not (Btst 55))
					(proc762_1 @local0 3000)
					(= cycles 1)
				else
					(= cycles 1)
				)
			)
			(5
				(if (not (Btst 55))
					(cls)
					(= seconds 2)
				else
					(= cycles 1)
				)
			)
			(6
				(if (not (Btst 55))
					(cedric
						init:
						setLoop: 4
						setCycle: Walk
						setPri: 15
						moveSpeed: 1
						ignoreActors: 1
						setMotion: MoveTo 246 52
					)
				)
				(= cycles 1)
			)
			(7
				(gMordObj setLoop: 3 cel: 0 setCycle: End self)
			)
			(8 (self dispose:))
		)
	)
)
