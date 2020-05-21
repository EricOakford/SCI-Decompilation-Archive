;;; Sierra Script 1.0 - (do not remove this comment)
(script# 550)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm550 0
)

(instance rm550 of Room
	(properties
		picture 550
	)
	
	(method (init)
		(super init:)
		(ego x: 124 y: 158 view: 24 loop: 0 cel: 0 init:)
		(gloryMonster init:)
		(Display {AND THE DANGER}
			p_font 2510
			p_at 86 164
			p_color 25
		)
		(Display {AND THE DANGER}
			p_font 2510
			p_at 85 163
			p_color 30
		)
		(self setScript: seeMeGo)
	)
	
	(method (cue)
		(curRoom newRoom: 130)
	)
)

(instance seeMeGo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(music number: 5 play: curRoom)
				(= seconds 2)
			)
			(2
				(gloryMonster loop: 1 cycleSpeed: 5 setCycle: CycleTo 1 1 self)
			)
			(3
				(ego cycleSpeed: 5 setCycle: EndLoop self)
			)
			(4
				(ego setCycle: BegLoop self)
				(gloryMonster setCycle: BegLoop self)
			)
			(5 0)
			(6
				(gloryMonster loop: 1 cycleSpeed: 5 setCycle: CycleTo 2 1 self)
			)
			(7 (ego setCycle: CycleTo 2 1 self))
			(8
				(gloryMonster setCycle: BegLoop self)
				(ego setCycle: BegLoop self)
			)
			(9 0)
			(10 (ego setCycle: CycleTo 4 1 self))
			(11
				(monsterGore init: setCycle: EndLoop self)
				(ego setCycle: EndLoop)
			)
			(12
				(monsterGore dispose:)
				(ego setCycle: BegLoop self)
				(if (not register) (DoAudio Play 6))
				(gloryMonster loop: 1 cycleSpeed: 5 setCycle: EndLoop self)
			)
			(13 0)
			(14
				(if (not register)
					(++ register)
					(self start: 3 init:)
				else
					(ego view: 0 dispose:)
					(gloryMonster dispose:)
					(DrawPic 130 IRISIN)
					(gloryDude init:)
					(III init:)
					(Display {BECOME A HERO AGAIN}
						p_font 2510
						p_at 61 170
						p_color 25
					)
					(Display {BECOME A HERO AGAIN}
						p_font 2510
						p_at 60 169
						p_color 30
					)
					(= seconds 2)
				)
			)
			(15 0)
			(16 0)
		)
	)
)

(instance monsterGore of Prop
	(properties
		x 198
		y 158
		view 24
		loop 1
	)
)

(instance gloryMonster of Prop
	(properties
		x 215
		y 139
		view 823
		loop 4
		priority 1
		signal fixPriOn
	)
)

(instance III of View
	(properties
		x 85
		y 121
		view 134
		loop 4
		cel 6
	)
)

(instance gloryDude of Prop
	(properties
		x 201
		y 135
		view 131
		loop 2
		cel 5
	)
)
