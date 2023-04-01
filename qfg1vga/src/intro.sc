;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include game.sh)
(use Main)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	intro 0
)

(local
	local0	;unused
)
(instance intro of Room
	(properties
		picture pIntro
	)
	
	(method (init)
		(LoadMany VIEW vIntroDragon vIntroFlame2 vQFGCooldown vHeroCooldown vIntroTitle vIntroFlame)
		(theIconBar disable:)
		(theGame setCursor: ARROW_CURSOR FALSE)
		(Bset fHideCursor)
		(self setScript: roomScript)
		(super init:)
	)
)

(instance dragon of Prop
	(properties
		x 260
		y 101
		view vIntroDragon
		priority 6
		signal fixPriOn
		cycleSpeed 8
	)
)

(instance dragonFire of Prop
	(properties
		x 147
		y 74
		view vIntroFlame2
		priority 15
		signal fixPriOn
	)
)

(instance subTitle of Prop
	(properties
		x 172
		y 167
		view vQFGCooldown
	)
)

(instance qForGlory of Prop
	(properties
		x 56
		y 154
		view vHeroCooldown
	)
)

(instance fireSound of Sound
	(properties
		number 46
	)
)

(instance playMusic of Script
	(method (doit)
		(super doit: &rest)
		(if (and (== state 0) (== (theMusic prevSignal?) -1))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic
					number: sShortIntro
					loop: 1
					play:
				)
			)
			(1
				(= cycles 5)
			)
			(2
				(theMusic
					loop: -1
					number: 61
					play:
				)
			)
		)
	)
)

(instance roomScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(dragon init:)
				(self setScript: playMusic)
				(= seconds 3)
			)
			(1
				(dragon setCel: 1)
				(= seconds 1)
			)
			(2
				(dragon setCycle: EndLoop self)
			)
			(3
				(dragon stopUpd:)
				(fireSound play:)
				(dragonFire init: setCycle: EndLoop self)
			)
			(4
				(qForGlory init: setCel: 0 setCycle: EndLoop self)
				(fireSound stop:)
				(dragonFire dispose:)
			)
			(5
				(qForGlory
					view: vIntroTitle
					setLoop: 0
					setCel: 0
				)
				(dragon startUpd: setCel: 6)
				(= ticks 25)
			)
			(6
				(qForGlory stopUpd:)
				(dragon stopUpd: setCel: 10)
				(fireSound play:)
				(dragonFire
					init:
					view: vIntroFlame
					posn: 170 144
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(7
				(fireSound stop:)
				(dragonFire dispose:)
				(subTitle init: setCel: 0 setCycle: EndLoop self)
			)
			(8
				(subTitle
					view: vIntroTitle
					setLoop: 1
					setCel: 0
				)
				(self cue:)
			)
			(9
				(cast eachElementDo: #dispose)
				(curRoom drawPic: pCredits FADEOUT)
				(self cue:)
			)
			(10
				(angel init:)
				(execProducer init:)
				(= seconds 8)
			)
			(11
				(curRoom
					drawPic: pCredits PIXELDISSOLVE
				)
				(angel dispose:)
				(dogLookingRight init:)
				(dogLookingLeft init:)
				(execProducer dispose:)
				(bill init:)
				(= seconds 8)
			)
			(12
				(curRoom
					drawPic: pCredits PIXELDISSOLVE
				)
				(dogLookingRight dispose:)
				(dogLookingLeft dispose:)
				(rowOfDogs init:)
				(bill dispose:)
				(stuart init:)
				(= seconds 8)
			)
			(13
				(curRoom
					drawPic: pCredits PIXELDISSOLVE
				)
				(rowOfDogs dispose:)
				(angel init:)
				(stuart dispose:)
				(director init:)
				(= seconds 8)
			)
			(14
				(curRoom
					drawPic: pCredits PIXELDISSOLVE
				)
				(angel dispose:)
				(dogLookingRight init:)
				(dogLookingLeft init:)
				(director dispose:)
				(gameDesigner init:)
				(= seconds 8)
			)
			(15
				(curRoom
					drawPic: pCredits PIXELDISSOLVE
				)
				(dogLookingRight dispose:)
				(dogLookingLeft dispose:)
				(rowOfDogs init:)
				(gameDesigner dispose:)
				(artDesigner init:)
				(= seconds 8)
			)
			(16
				(curRoom
					drawPic: pCredits PIXELDISSOLVE
				)
				(rowOfDogs dispose:)
				(angel init:)
				(artDesigner dispose:)
				(leadProg init:)
				(= seconds 8)
			)
			(17
				(curRoom
					drawPic: pCredits PIXELDISSOLVE
				)
				(angel dispose:)
				(lion init:)
				(leadProg dispose:)
				(composer init:)
				(= seconds 8)
			)
			(18
				(curRoom newRoom: CHARSEL)
			)
		)
	)
)

(instance angel of View
	(properties
		x 203
		y 69
		view pCredits
		loop 1
	)
)

(instance bill of View
	(properties
		x 161
		y 109
		view pCredits
	)
)

(instance execProducer of View
	(properties
		x 161
		y 109
		view pCredits
		loop 2
	)
)

(instance director of View
	(properties
		x 161
		y 109
		view pCredits
		loop 3
	)
)

(instance stuart of View
	(properties
		x 161
		y 109
		view pCredits
		loop 4
	)
)

(instance dogLookingRight of View
	(properties
		x 172
		y 172
		view pCredits
		loop 5
	)
)

(instance dogLookingLeft of View
	(properties
		x 147
		y 49
		view pCredits
		loop 6
	)
)

(instance gameDesigner of View
	(properties
		x 162
		y 109
		view pCredits
		loop 7
	)
)

(instance artDesigner of View
	(properties
		x 162
		y 109
		view pCredits
		loop 8
	)
)

(instance rowOfDogs of View
	(properties
		x 164
		y 173
		view pCredits
		loop 9
	)
)

(instance leadProg of View
	(properties
		x 154
		y 109
		view pCredits
		loop 10
	)
)

(instance lion of View
	(properties
		x 159
		y 69
		view 1001
	)
)

(instance composer of View
	(properties
		x 161
		y 76
		view pCredits
		loop 11
	)
)
