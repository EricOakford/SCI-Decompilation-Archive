;;; Sierra Script 1.0 - (do not remove this comment)
(script# DELTAUR_DEATH) ;707
(include game.sh)
(use Main)
(use Deltaur)
(use RangeOsc)
(use Motion)
(use System)

(public
	dropGrenade 0
	egoShot 1
)

(local
	local0
	[local1 2]
)
(instance dropGrenade of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if 0 (ego setLoop: 0) else (ego setLoop: 1))
				(if (== (DeltaurRegion egoStatus?) egoWithHelmet)
					(ego setLoop: (+ (ego loop?) 2))
				)
				(ego view: 75 cel: 0 cycleSpeed: 8 setCycle: CycleTo 7 1 self)
			)
			(1
				(soundFx number: 518 loop: 1 play:)
				(globalSound number: 519 loop: 1 play:)
				(ego cel: 8 setCycle: CycleTo 12 1)
				(= seconds 2)
			)
			(2 (ego setCycle: CycleTo 13 1 self))
			(3
				(ego setCycle: RangeOsc 3 14 15 self)
			)
			(4
				(EgoDead 934 0 0
					{Although you've always had an appreciation for the gases of the universe, this one proved a bit overwhelming. 
					It really was a great idea, though. However, you should have chosen a victim not so close to yourself. 
					Better choice next time.}
				)
				(self dispose:)
			)
		)
	)
)

(instance egoShot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					((== (DeltaurRegion egoStatus?) egoWithHelmet)
						(if (OneOf (ego loop?) 0 4 6 3)
							(ego setLoop: 6)
						else
							(ego setLoop: 7)
						)
					)
					((> (ego loop?) 3) (ego setLoop: (- (ego loop?) 2)))
					(else
						(switch (ego loop?)
							(2
								(if (> (ego x?) local0) (ego setLoop: 3))
							)
							(3
								(if (> (ego x?) local0)
									(ego setLoop: 5)
								else
									(ego setLoop: 4)
								)
							)
						)
					)
				)
				(if (== (DeltaurRegion egoStatus?) 2)
					(ego view: 50)
				else
					(ego view: 48 setLoop: 1)
				)
				(soundFx number: 368 loop: 1 play:)
				(ego cel: 0 setCycle: EndLoop self)
			)
			(1
				(EgoDead 940 2 0
					{BLAMMO! Those pulseray pistols sure are effective little weapons. 
					Even after it kills you, your meat continues to cook to nice golden brown. 
					Try not to get shot in the future. 
					Even if your life isn't worth much, think about the rest of the universe.}
				)
				(self dispose:)
			)
		)
	)
)
