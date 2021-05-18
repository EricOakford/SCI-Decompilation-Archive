;;; Sierra Script 1.0 - (do not remove this comment)
(script# 215)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	showerReg 0
)

(local
	local0
)
(instance showerReg of Room
	(properties
		picture 71
		style DISSOLVE
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(Load SOUND 8)
		(myMusic number: 7 loop: -1 play:)
		(spike setPri: 9 init: stopUpd: hide:)
		(door init: stopUpd:)
		(killer setLoop: 2 setPri: 4 init: stopUpd:)
		(water
			view: 271
			loop: 1
			cel: 0
			posn: 212 35
			setPri: 11
			setCycle: Forward
			init:
		)
		(knife
			view: 271
			setLoop: 3
			cel: 0
			posn: 62 83
			init:
			stopUpd:
		)
		(hand
			view: 171
			loop: 6
			cel: 0
			posn: 187 57
			setPri: 11
			init:
			stopUpd:
		)
		(shadow
			view: 171
			loop: 0
			cel: 0
			posn: 189 81
			setPri: 11
			init:
			setScript: showerUp
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance showerUp of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(shadow setCycle: EndLoop self)
			)
			(1
				(shadow cel: 0 loop: 1 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(2
				(shadow cel: 0 loop: 2 setCycle: EndLoop self)
			)
			(3
				(door setCycle: EndLoop self)
				(spike show:)
				(shadow setLoop: 3 cel: 0 cycleSpeed: 0 setCycle: Forward)
			)
			(4
				(killer setCycle: Walk setMotion: MoveTo 30 115 self)
			)
			(5
				(door setCycle: BegLoop self)
				(spike setCycle: BegLoop)
			)
			(6
				(door dispose:)
				(spike dispose:)
				(shadow
					loop: 2
					cel: (- (NumCels shadow) 1)
					cycleSpeed: 1
					setCycle: BegLoop self
				)
			)
			(7
				(shadow loop: 4 setCycle: EndLoop self)
			)
			(8
				(shadow loop: 5 cel: 0 cycleSpeed: 0 setCycle: Forward)
				(= seconds 3)
			)
			(9
				(myMusic stop:)
				(myMusic number: 8 loop: 1 play:)
				(knife setCycle: EndLoop setMotion: MoveTo 187 107 self)
			)
			(10
				(knife dispose:)
				(hand setCycle: EndLoop self)
			)
			(11
				(if (< (++ local0) 4)
					(switch local0
						(1
							(blood1
								view: 171
								loop: 7
								setPri: 12
								posn: 187 77
								setCycle: EndLoop
								init:
							)
						)
						(2
							(blood2
								view: 171
								loop: 8
								setPri: 12
								posn: 197 87
								setCycle: EndLoop
								init:
							)
						)
						(3
							(blood3
								view: 171
								loop: 9
								setPri: 12
								posn: 177 97
								setCycle: EndLoop
								init:
							)
							(blood4
								view: 271
								loop: 6
								setPri: 12
								posn: 180 122
								cycleSpeed: 1
								setCycle: EndLoop
								init:
							)
						)
					)
					(= state 9)
					(shadow view: 271 loop: 5 cycleSpeed: 1 setCycle: EndLoop)
					(self cue:)
				else
					(self cue:)
				)
			)
			(12
				(drip
					view: 271
					loop: 7
					posn: 181 145
					setCycle: Forward
					cycleSpeed: 1
					init:
				)
				(shadow setCycle: EndLoop self)
			)
			;NOTE: This case already exists, and will probably never be executed
;;;			(12
;;;				(= seconds 7)
;;;			)
			(13
				(= cIcon 271)
				(= deathLoop 9)
				(EgoDead 215 0)
			)
		)
	)
)

(instance shadow of Actor)

(instance killer of Actor
	(properties
		y 95
		view 271
	)
)

(instance knife of Actor)

(instance door of Prop
	(properties
		y 49
		view 271
	)
)

(instance spike of Prop
	(properties
		y 50
		view 271
		loop 4
	)
)

(instance water of Prop)

(instance hand of Prop)

(instance blood1 of Prop)

(instance blood2 of Prop)

(instance blood3 of Prop)

(instance blood4 of Prop)

(instance drip of Prop)

(instance myMusic of Sound)
