;;; Sierra Script 1.0 - (do not remove this comment)
(script# 410)
(include game.sh)
(use Main)
(use LLRoom)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm410 0
)

(local
	local0
)
(instance rm410 of LLRoom
	(properties
		picture 410
		east 400
		south 415
		west 420
	)
	
	(method (init)
		(ego init: normalize: 411)
		(LoadMany 128 410 411 412)
		(LoadMany 132 410 420)
		(switch prevRoomNum
			(south
				(ego x: 157 y: 125 setLoop: 3 setCel: 0 setCycle: 0)
				(reflection
					init:
					x: 157
					y: 125
					setLoop: 4
					setCel: 0
					setCycle: 0
				)
				(desmond
					init:
					x: 130
					y: 125
					setLoop: 4
					setCel: 0
					setCycle: 0
					signal: 16384
				)
				(self setScript: sCartoon2)
			)
			(else 
				(desmond init: setCycle: Forward)
				(self setScript: sCartoon)
			)
		)
		(super init:)
		(steam init: setCycle: Forward)
		(arrow init: setCycle: Forward)
	)
	
	(method (newRoom n)
		(if (!= n 415)
			(theMusic fade: 0 15 12 1 self)
		)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(super newRoom: n)
	)
)

(instance steam of Prop
	(properties
		x 133
		y 162
		view 410
		cel 4
		cycleSpeed 12
		detailLevel 3
	)
)

(instance desmond of Actor
	(properties
		x 31
		y 121
		view 412
		loop 2
	)
)

(instance arrow of Prop
	(properties
		x 286
		y 75
		view 410
		loop 1
		cel 1
		priority 15
		signal fixPriOn
		detailLevel 4
	)
)

(instance reflection of Actor
	(properties
		view 411
		loop 2
		priority 1
		signal fixPriOn
	)
	
	(method (doit)
		(= x (ego x?))
		(= y (+ 125 (- 125 (ego y?))))
		(= cel (ego cel?))
	)
)

(instance sCartoon of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					x: 262
					y: 111
					cycleSpeed: 6
					moveSpeed: 6
					setLoop: 0
					setMotion: MoveTo 226 125 self
				)
				(reflection init:)
				(theMusic number: 410 flags: 1 setLoop: -1 play:)
			)
			(1
				(TimePrint 410 0 #at -1 15 70 280)
				(ego setLoop: 0 setMotion: MoveTo 157 125 self)
				(desmond setScript: sDesmondCartoon)
				(= cycles 20)
			)
			(2
				(TimePrint 410 1 #at -1 20)
				(TimePrint 410 2 #at -1 15 #width 280)
			)
			(3
				(ego setLoop: 3 setCel: 0 setCycle: 0)
				(reflection setLoop: 4 setCel: 0 setCycle: 0)
				(self dispose:)
			)
		)
	)
)

(instance sDesmondCartoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(desmond setLoop: 3 setCel: 0 setCycle: EndLoop self)
			)
			(1
				(desmond
					setLoop: 0
					setCel: 0
					setCycle: Walk
					setMotion: MoveTo 130 125 self
				)
			)
			(2
				(desmond setLoop: 4 setCel: 0 setCycle: 0)
				(= seconds 3)
			)
			(3 (curRoom newRoom: 415))
		)
	)
)

(instance sCartoon2 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 420 flags: mNOPAUSE setLoop: -1 play:)
				(= ticks 123)
			)
			(1
				(reflection loop: 2)
				(ego
					cycleSpeed: 6
					moveSpeed: 6
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 139 125 self
				)
				(desmond
					setLoop: 1
					setCel: 0
					cycleSpeed: (ego cycleSpeed?)
					moveSpeed: (ego moveSpeed?)
					setCycle: Walk
					setMotion: MoveTo -10 138
				)
			)
			(2
				(reflection dispose:)
				(ego setMotion: MoveTo 0 145 self)
			)
			(3
				(steam dispose:)
				(arrow dispose:)
				(desmond dispose:)
				(ego dispose:)
				(DrawPic 1 -32761)
				(DoDisplay 4 myDisplayColor 410 3)
				(= seconds 7)
			)
			(4 (curRoom newRoom: 420))
		)
	)
)
