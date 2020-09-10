;;; Sierra Script 1.0 - (do not remove this comment)
(script# rMainStreet)
(include game.sh)
(use Main)
(use Print)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm310 0
)

(local
	guildDoor
	oldLadyDoor
	oldTime
)
(instance rm310 of Room
	(properties
		picture rMainStreet
		style FADEOUT
	)
	
	(method (init)
		(Load PICTURE rMagicShop)
		(LoadMany VIEW rMagicShop rMainStreet vZara vZaraBust)
		(super init: &rest)
		(townSound play:)
		(ego view: vEgo setLoop: 6 x: 208 y: 188 init:)
		(theEye view: rMainStreet init: setCel: 5)
		(LOL view: rMainStreet init: cycleSpeed: 6 setCycle: Forward)
		((= oldLadyDoor (Prop new:))
			view: rMainStreet
			loop: 1
			x: 217
			y: 106
			setPri: 5
			init:
		)
		((= guildDoor (Prop new:))
			view: rMainStreet
			loop: 2
			x: 16
			y: 150
			priority: 11
			signal: (| ignrAct ignrHrz)
			init:
		)
		(self setScript: roomScript)
	)
	
	(method (doit)
		(if (== (witchSound prevSignal?) -1)
			(soundFx number: sMagicShop loop: -1 flags: 0 play:)
			(witchSound prevSignal: 0)
		)
		(super doit:)
		(if
			(and
				(> (Abs (- gameTime oldTime)) 6)
				(== (curRoom curPic?) rMainStreet)
			)
			(= oldTime gameTime)
			(Palette PALCycle 234 241 -1)
			(Palette PALCycle 242 250 -1)
			(Palette PALCycle 251 254 -1)
		)
	)
)

(instance theEye of Prop
	(properties
		x 259
		y 47
		view rMainStreet
		loop 3
		cel 6
		priority 10
		signal (| ignrAct fixPriOn)
	)
)

(instance LOL of Prop
	(properties
		x 156
		y 113
		view rMainStreet
		loop 6
	)
)

(instance transEgoEye of Prop
	(properties
		x 258
		y 156
		view rMainStreet
		priority 11
		signal (| ignrAct fixPriOn)
		cycleSpeed 10
	)
)

(instance zara of Prop
	(properties
		x 259
		y 100
		view vZara
		loop 1
		priority 2
		signal (| ignrAct fixPriOn)
	)
)

(instance zaraPort of View
	(properties
		x 14
		y 12
		view vZaraBust
		priority 14
		signal fixPriOn
	)
)

(instance zaraMouth of Prop
	(properties
		x 87
		y 43
		view vZaraBust
		loop 1
		cel 5
		priority 15
		signal fixPriOn
		cycleSpeed 12
	)
)

(instance zaraEyes of Prop
	(properties
		x 87
		y 26
		view vZaraBust
		loop 2
		cel 1
		priority 15
		signal fixPriOn
		cycleSpeed 24
	)
)

(instance damiano of Prop
	(properties
		x 203
		y 60
		view rMagicShop
		loop 6
		priority 15
		signal fixPriOn
		cycleSpeed 12
	)
)

(instance magicFlash of Prop
	(properties
		x 295
		y 80
		view rMagicShop
		loop 5
		priority 15
		signal fixPriOn
		cycleSpeed 8
	)
)

(instance glowCoals of Prop
	(properties
		x 240
		y 115
		view rMagicShop
		loop 4
		cel 1
	)
)

(instance fishBowl of Prop
	(properties
		x 308
		y 149
		view rMagicShop
		loop 3
		cel 2
	)
)

(instance burnSent of Prop
	(properties
		x 58
		y 123
		view rMagicShop
		loop 2
		priority 6
		signal fixPriOn
	)
)

(instance damiFoot of View
	(properties
		x 194
		y 52
		view rMagicShop
		loop 8
	)
)

(instance transFormEgo of Prop
	(properties
		x 101
		y 177
		view rMagicShop
	)
)

(instance suckedSound of Sound
	(properties
		number sFireDart
	)
)

(instance townSound of Sound
	(properties
		number sTown
		loop -1
	)
)

(instance witchSound of Sound
	(properties
		number sTeleport
	)
)

(instance witchHereSound of Sound
	(properties
		number sMagicShop
		loop -1
	)
)

(instance roomScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 2)
			)
			(1
				(Print
					addText: {A world you've never seen before.}
					modeless: TRUE
					x: 80
					y: 10
					init:
				)
				(ego setMotion: MoveTo 213 189 self)
				(theEye cycleSpeed: 10 setCel: 5 setCycle: CycleTo 1 -1)
			)
			(2
				(soundFx fade:)
				(ego setMotion: MoveTo 238 177 self)
			)
			(3
				(ego setLoop: 6 setMotion: MoveTo 296 140 self)
			)
			(4
				(ego hide:)
				(theEye setCel: 3)
				(townSound fade:)
				(= ticks 90)
			)
			(5
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(theEye dispose:)
				(LOL dispose:)
				(guildDoor dispose:)
				(oldLadyDoor dispose:)
				(transEgoEye dispose:)
				(= currentPic rMagicShop)
				(curRoom drawPic: rMagicShop FADEOUT)
				(transFormEgo init: posn: 101 176)
				(damiano init: setCel: 0)
				(glowCoals init: setCycle: Forward)
				(fishBowl init: setCycle: Forward)
				(burnSent init: setCycle: Forward)
				(ego show: view: vEgoStanding setLoop: 6 posn: 87 185)
				(Load VIEW vZaraBust)
				(= cycles 2)
			)
			(6
				(transFormEgo setCycle: EndLoop self)
			)
			(7
				(transFormEgo stopUpd: addToPic:)
				(damiano setCycle: EndLoop self)
				(magicFlash init:)
			)
			(8
				(witchSound play:)
				(magicFlash setCycle: EndLoop self)
			)
			(9
				(damiano stopUpd:)
				(zara init:)
				(magicFlash hide:)
				(zara setCycle: EndLoop self)
			)
			(10
				(zaraPort init: addToPic:)
				(zaraEyes
					init:
					setCel: 3
					setLoop: 2
					posn: 58 33
					setScript: sEyes
				)
				(zaraMouth init: setLoop: 1 posn: 60 48 setCycle: Forward)
				(zara setLoop: 4 setCel: 0 setCycle: EndLoop)
				(= cycles 2)
			)
			(11
				(Print
					addText: {So, you have returned.\nWhat is it you seek?}
					modeless: TRUE
					x: 150
					y: 10
					init:
				)
				(= seconds 8)
			)
			(12
				(zaraEyes setCel: 3 setScript: 0)
				(zaraMouth setCycle: BegLoop self)
			)
			(13
				(= ticks 30)
			)
			(14
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(curRoom newRoom: rDemoGraveyard)
			)
		)
	)
)

(instance sEyes of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (Random 30 90))
			)
			(1
				(client setCel: 1)
				(= ticks 30)
			)
			(2
				(client setCel: 3)
				(self init:)
			)
		)
	)
)
