;;; Sierra Script 1.0 - (do not remove this comment)
(script# 3)
(include game.sh)
(use Main)
(use Intrface)
(use RandCyc)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demoRoom3 0
)

(instance demoRoom3 of Room
	(properties
		picture 310
		style IRISIN
	)
	
	(method (init)
		(LoadMany VIEW 312 313 314 315 317 318)
		(Load SOUND 1001)
		(ego setPri: -1)
		(barfly init:)
		(barfly1 init:)
		(ziggy init:)
		(woman2 init:)
		(dancersA init:)
		(dancersB init:)
		(barfly2 setScript: sAnimatePlayers init:)
		(dancersC setCycle: Forward init:)
		(flapper setCycle: Forward init:)
		(pianoplayer setCycle: Forward init:)
		(sleeper setCycle: Forward init:)
		(woman1 setCycle: RandCycle init:)
		(super init:)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(barMusic play:)
				(= seconds 1)
			)
			(1
				(Print 3 0 #dispose #at 20 145 #width 270 #mode teJustCenter)
				(= seconds 9)
			)
			(2
				(barMusic fade:)
				(= seconds 2)
			)
			(3
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(UnLoad PICTURE 310)
				(curRoom newRoom: 4)
			)
		)
	)
)

(instance sAnimatePlayers of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(dancersA setScript: sRDancers)
				(dancersB setScript: sMDancers)
				(= cycles 4)
			)
			(1
				(barfly setScript: sBartender)
				(barfly1 setScript: sBarDrinks)
				(ziggy setScript: sZiggySmokes)
				(woman2 setScript: sNodder)
				(= cycles 4)
			)
			(2 (self dispose:))
		)
	)
)

(instance sRDancers of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(dancersA view: 313 loop: 0 setCycle: Forward)
				(= seconds (Random 5 10))
			)
			(1
				(dancersA view: 314 loop: 0 setCycle: EndLoop self)
			)
			(2
				(dancersA view: 313 loop: 1 setCycle: Forward)
				(= seconds (Random 4 6))
			)
			(3
				(dancersA view: 314 loop: 1 setCycle: EndLoop self)
			)
			(4 (self init:))
		)
	)
)

(instance sMDancers of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(dancersB view: 313 loop: 2 setCycle: Forward)
				(= seconds (Random 4 7))
			)
			(1
				(dancersB view: 314 loop: 2 setCycle: EndLoop self)
			)
			(2
				(dancersB view: 313 loop: 3 setCycle: Forward)
				(= seconds (Random 4 8))
			)
			(3
				(dancersB view: 314 loop: 2 cel: 11 setCycle: BegLoop self)
			)
			(4 (self init:))
		)
	)
)

(instance sBartender of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(barfly setLoop: 2 moveSpeed: 8 cycleSpeed: 8)
				(= seconds (Random 4 10))
			)
			(1
				(switch (Random 0 1)
					(0
						(barfly setLoop: 1 setMotion: MoveTo 55 113 self)
					)
					(1
						(barfly setLoop: 0 setMotion: MoveTo 7 123 self)
					)
				)
			)
			(2
				(if (== (barfly x?) 55)
					(barfly setLoop: 0)
				else
					(barfly setLoop: 1)
				)
				(barfly setMotion: MoveTo 47 115 self)
			)
			(3 (self init:))
		)
	)
)

(instance sBarDrinks of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (barfly1 setCycle: EndLoop self))
			(1 (= seconds (Random 1 6)))
			(2 (barfly1 setCycle: BegLoop self))
			(3 (= seconds (Random 2 4)))
			(4 (self init:))
		)
	)
)

(instance sZiggySmokes of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ziggy setCycle: CycleTo 13 1 self)
			)
			(1 (smoke setCycle: EndLoop self))
			(2
				(ziggy setCycle: EndLoop self)
				(smoke cel: 0)
			)
			(3 (= seconds (Random 1 6)))
			(4
				(switch (Random 0 2)
					(0 (++ state) (= cycles 1))
					(else 
						(ziggy cel: 3 setCycle: CycleTo 13 1 self)
					)
				)
			)
			(5 (smoke setCycle: EndLoop self))
			(6
				(ziggy cel: 3 setCycle: CycleTo 0 -1 self)
				(smoke cel: 0)
			)
			(7 (= seconds (Random 2 4)))
			(8 (self init:))
		)
	)
)

(instance sNodder of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (woman2 setCycle: EndLoop self))
			(1 (= cycles (Random 1 10)))
			(2 (woman2 setCycle: BegLoop self))
			(3 (= cycles (Random 10 20)))
			(4 (self init:))
		)
	)
)

(instance barfly of Actor
	(properties
		x 47
		y 115
		view 312
		loop 2
	)
)

(instance barfly1 of Actor
	(properties
		x 59
		y 148
		view 312
		loop 3
		priority 12
		signal fixPriOn
	)
)

(instance barfly2 of Actor
	(properties
		x 81
		y 140
		view 312
		loop 4
		priority 12
		signal fixPriOn
	)
)

(instance ziggy of Prop
	(properties
		x 292
		y 124
		view 318
		cel 5
		priority 12
		signal fixPriOn
	)
	
	(method (init)
		(smoke posn: (+ (self x?) 10) (- (self y?) 14) init:)
		(super init:)
	)
)

(instance smoke of Prop
	(properties
		view 318
		loop 6
	)
)

(instance flapper of Prop
	(properties
		x 141
		y 87
		view 315
		cel 1
	)
)

(instance pianoplayer of Prop
	(properties
		x 116
		y 83
		view 315
		loop 1
		cel 13
	)
)

(instance sleeper of Prop
	(properties
		x 287
		y 117
		view 317
		loop 3
		cel 3
		cycleSpeed 10
	)
)

(instance woman1 of Prop
	(properties
		x 261
		y 100
		view 317
		loop 2
		cel 3
	)
)

(instance woman2 of Prop
	(properties
		x 236
		y 101
		view 317
		loop 4
		cycleSpeed 12
	)
)

(instance dancersA of Prop
	(properties
		x 183
		y 111
		view 313
		cel 1
	)
)

(instance dancersB of Prop
	(properties
		x 151
		y 121
		view 313
		loop 3
		cel 5
	)
)

(instance dancersC of Prop
	(properties
		x 106
		y 114
		view 313
		loop 4
		cel 2
	)
)

(instance barMusic of Sound
	(properties
		number 1001
		loop -1
	)
)
