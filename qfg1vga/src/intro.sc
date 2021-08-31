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
	local0
)
(instance intro of Room
	(properties
		picture 902
	)
	
	(method (init)
		(LoadMany VIEW 910 907 908 909 912 906)
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
		view 910
		priority 6
		signal fixPriOn
		cycleSpeed 8
	)
)

(instance dragonFire of Prop
	(properties
		x 147
		y 74
		view 907
		priority 15
		signal fixPriOn
	)
)

(instance subTitle of Prop
	(properties
		x 172
		y 167
		view 908
	)
)

(instance qForGlory of Prop
	(properties
		x 56
		y 154
		view 909
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
		(if (and (== state 0) (== (cSound prevSignal?) -1))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound number: 999 loop: 1 play:)
			)
			(1
				(= cycles 5)
			)
			(2
				(cSound loop: -1 number: 61 play:)
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
			(2 (dragon setCycle: EndLoop self))
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
				(qForGlory view: 912 setLoop: 0 setCel: 0)
				(dragon startUpd: setCel: 6)
				(= ticks 25)
			)
			(6
				(qForGlory stopUpd:)
				(dragon stopUpd: setCel: 10)
				(fireSound play:)
				(dragonFire
					init:
					view: 906
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
				(subTitle view: 912 setLoop: 1 setCel: 0)
				(self cue:)
			)
			(9
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 903 FADEOUT)
				(self cue:)
			)
			(10
				(angel init:)
				(execProducer init:)
				(= seconds 8)
			)
			(11
				(curRoom drawPic: 903 PIXELDISSOLVE)
				(angel dispose:)
				(dogLookingRight init:)
				(dogLookingLeft init:)
				(execProducer dispose:)
				(bill init:)
				(= seconds 8)
			)
			(12
				(curRoom drawPic: 903 PIXELDISSOLVE)
				(dogLookingRight dispose:)
				(dogLookingLeft dispose:)
				(rowOfDogs init:)
				(bill dispose:)
				(stuart init:)
				(= seconds 8)
			)
			(13
				(curRoom drawPic: 903 PIXELDISSOLVE)
				(rowOfDogs dispose:)
				(angel init:)
				(stuart dispose:)
				(director init:)
				(= seconds 8)
			)
			(14
				(curRoom drawPic: 903 PIXELDISSOLVE)
				(angel dispose:)
				(dogLookingRight init:)
				(dogLookingLeft init:)
				(director dispose:)
				(gameDesigner init:)
				(= seconds 8)
			)
			(15
				(curRoom drawPic: 903 PIXELDISSOLVE)
				(dogLookingRight dispose:)
				(dogLookingLeft dispose:)
				(rowOfDogs init:)
				(gameDesigner dispose:)
				(artDesigner init:)
				(= seconds 8)
			)
			(16
				(curRoom drawPic: 903 PIXELDISSOLVE)
				(rowOfDogs dispose:)
				(angel init:)
				(artDesigner dispose:)
				(leadProg init:)
				(= seconds 8)
			)
			(17
				(curRoom drawPic: 903 PIXELDISSOLVE)
				(angel dispose:)
				(lion init:)
				(leadProg dispose:)
				(composer init:)
				(= seconds 8)
			)
			(18
				(curRoom newRoom: 202)
			)
		)
	)
)

(instance angel of View
	(properties
		x 203
		y 69
		view 903
		loop 1
	)
)

(instance bill of View
	(properties
		x 161
		y 109
		view 903
	)
)

(instance execProducer of View
	(properties
		x 161
		y 109
		view 903
		loop 2
	)
)

(instance director of View
	(properties
		x 161
		y 109
		view 903
		loop 3
	)
)

(instance stuart of View
	(properties
		x 161
		y 109
		view 903
		loop 4
	)
)

(instance dogLookingRight of View
	(properties
		x 172
		y 172
		view 903
		loop 5
	)
)

(instance dogLookingLeft of View
	(properties
		x 147
		y 49
		view 903
		loop 6
	)
)

(instance gameDesigner of View
	(properties
		x 162
		y 109
		view 903
		loop 7
	)
)

(instance artDesigner of View
	(properties
		x 162
		y 109
		view 903
		loop 8
	)
)

(instance rowOfDogs of View
	(properties
		x 164
		y 173
		view 903
		loop 9
	)
)

(instance leadProg of View
	(properties
		x 154
		y 109
		view 903
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
		view 903
		loop 11
	)
)
