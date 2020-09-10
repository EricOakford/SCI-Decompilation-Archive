;;; Sierra Script 1.0 - (do not remove this comment)
(script# rDemoGraveyard)
(include game.sh)
(use Main)
(use Print)
(use MoveCyc)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm11 0
)

(local
	ghostCount
	local1 = [0 5]
	ghost1Path = [
		0 0 180 27
		0 1 196 47
		0 2 190 67
		0 3 153 80
		0 4 132 91
		0 5 152 116
		0 6 189 114
		0 7 198 96
		0 8 179 75
		0 9 159 57
		0 10 174 35
		PATHEND
	]
	ghost4Path = [
		4 0 232 95
		4 1 229 94
		4 2 229 94
		4 3 227 94
		4 4 221 95
		4 5 215 96
		4 6 206 96
		4 7 200 96
		4 8 194 95
		4 9 193 95
		4 10 193 95
		4 11 193 95
		4 12 193 95
		4 13 193 95
		4 14 193 95
		4 15 193 95
		PATHEND
	]
)
(instance rm11 of Room
	(properties
		picture rDemoGraveyard
		style FADEOUT
	)
	
	(method (init)
		(Load PICTURE pForestArena)
		(LoadMany VIEW
			vGhosts
			vEgoStabSword
			vEgoHitSword
			vMoreDragon
		)
		(super init: &rest)
		(ego
			view: vEgo
			setLoop: -1
			setCycle: Walk
			posn: -15 147
			init:
		)
		(if (not prevRoomNum)
			(soundFx
				number: sMagicShop
				loop: -1
				flags: 0
				play:
			)
		)
		(self setScript: roomScript)
	)
)

(instance ghost1 of Actor
	(properties
		x 180
		y 27
		view vGhosts
		signal ignrAct
		cycleSpeed 9
	)
	
	(method (cue)
		(super cue:)
		(switch (++ ghostCount)
			(1
				(self setCycle: MoveCycle @ghost1Path self)
			)
			(2
				(roomScript cue:)
			)
		)
	)
)

(instance ghost2 of Actor
	(properties
		x 168
		y 77
		view vGhosts
		loop 1
		signal ignrAct
		cycleSpeed 9
	)
)

(instance ghost3 of Actor
	(properties
		x 73
		y 139
		view vGhosts
		loop 2
		signal ignrAct
		cycleSpeed 12
	)
	
	(method (cue)
		(super cue:)
		(self
			setLoop: 3
			setCel: 0
			posn: 74 112
			cycleSpeed: 6
			setCycle: Forward
		)
	)
)

(instance ghost4 of Actor
	(properties
		x 232
		y 95
		view vGhosts
		loop 4
		signal ignrAct
	)
)

(instance combatEgo of Prop
	(properties
		x 105
		y 168
		view vEgoStabSword
	)
)

(instance dragon of Prop
	(properties
		x 183
		y 148
		view vMoreDragon
		cel 7
	)
	
	(method (doit)
		(super doit:)
		(= cycleSpeed (Random 6 24))
	)
)

(instance tail of Prop
	(properties
		x 203
		y 102
		view vMoreDragon
		loop 1
		cel 1
		cycleSpeed 2
	)
)

(instance roomScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 24 147 self)
			)
			(1
				(ego view: vEgoStanding)
				(ghost2 init: setScript: sMoan1Sc setCycle: EndLoop self)
				(ghost3 init: setCycle: EndLoop ghost3)
			)
			(2
				(ghost2 hide:)
				(ghost1
					init:
					cycleSpeed: 12
					setCycle: MoveCycle @ghost1Path ghost1
				)
			)
			(3
				(ghost1 setCel: 11 posn: 159 10)
				(ghost4 init: cycleSpeed: 12 setCycle: MoveCycle @ghost4Path)
				(Print
					addText: {You will face deadly dangers.}
					modeless: TRUE
					x: 60
					y: 165
					init:
				)
				(= ticks 10)
			)
			(4
				(ghost1 setCel: 12 posn: 167 0)
				(= ticks 10)
			)
			(5
				(ghost1 setCel: 13 posn: 176 4)
				(= ticks 10)
			)
			(6
				(ghost1 dispose:)
				(= seconds 6)
			)
			(7
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(ghost2 dispose:)
				(ghost3 dispose:)
				(ghost4 dispose:)
				(ego view: vEgo dispose:)
				(= currentPic pForestArena)
				(curRoom drawPic: pForestArena FADEOUT)
				(combatEgo
					init:
					view: vEgoSwingSword
					posn: 101 173
					setScript: combatEgoScript
				)
				(dragon init: setLoop: 0 posn: 168 129)
				(tail init: posn: 183 87 setLoop: 2 setScript: sTail)
				(soundFx stop:)
				(battleSound play: self)
				(= cycles 2)
			)
			(8
				(Load SOUND sRoar)
				(Print
					addText: {So you still want to Be a Hero?}
					modeless: TRUE
					x: 65
					y: 5
					init:
				)
				(= seconds 9)
			)
			(9)
			(10
				(combatEgo setCel: 0 setScript: 0)
				(battleSound client: 0)
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(curRoom newRoom: rBigEgo)
			)
		)
	)
)

(instance sDragon of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (Random 120 250))
			)
			(1
				(dragon setLoop: 0 setCel: 0 setCycle: EndLoop self)
				(dragonRoar play: self)
			)
			(2)
			(3
				(self init:)
			)
		)
	)
)

(instance sMoan1Sc of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register (Random 0 1))
				(switch register
					(0 (ghost1Snd play: self))
					(1 (ghost2Snd play: self))
				)
			)
			(1 (= ticks 30))
			(2
				(switch register
					(0 (ghost2Snd play: self))
					(1 (ghost1Snd play: self))
				)
			)
			(3
				(self dispose:)
			)
		)
	)
)

(instance dragonRoar of Sound
	(properties
		number sRoar
	)
)

(instance ghost1Snd of Sound
	(properties
		number sMoan2
	)
)

(instance ghost2Snd of Sound
	(properties
		number sMoan1
	)
)

(instance battleSound of Sound
	(properties
		number sShortBattle
		loop -1
	)
)

(instance sTail of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (Random 60 120))
			)
			(1
				(client
					setCel: 0
					cycleSpeed: (Random 3 9)
					setCycle: EndLoop self
				)
			)
			(2
				(= ticks (Random 60 120))
			)
			(3
				(client cycleSpeed: (Random 3 9) setCycle: BegLoop self)
			)
			(4
				(self init:)
			)
		)
	)
)

(instance combatEgoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (Random 100 300))
			)
			(1
				(client setCycle: CycleTo 4 1 self)
			)
			(2
				(dragon setScript: 0 setLoop: 1 setCel: 0 setCycle: EndLoop)
				(dragonRoar play:)
				(= ticks 20)
			)
			(3
				(client setCycle: EndLoop self)
			)
			(4
				(dragon setLoop: 0 setCycle: Forward)
				(client setCel: 0)
				(self init:)
			)
		)
	)
)
