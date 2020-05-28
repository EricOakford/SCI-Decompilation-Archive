;;; Sierra Script 1.0 - (do not remove this comment)
(script# 110)
(include game.sh)
(use Main)
(use Osc)
(use MoveCyc)
(use LoadMany)
(use Reverse)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm110 0
)

(local
	local0 =  4
	local1
	[walkToSnakeCycle 225] = [3 2 140 76 3 3 151 86 3 5 160 104 3 6 167 131 3 7 171 155 3 8 153 189 -32768]
	local227 =  1
)
(instance rm110 of Room
	(properties
		picture 240
		style PIXELDISSOLVE
	)
	
	(method (init)
		(LoadMany RES_VIEW 0 205 6 7)
		(LoadMany RES_SOUND 200 241 240)
		(theMusic client: self fade: 0 5 11 1)
		(sloth init: setScript: eatIt)
		(hyacinth init: setScript: (birdMove new:))
		(rickyMac init: setScript: (birdMove new:))
		(lucyMac init: setScript: (birdMove new:))
		(fredMac init: setScript: (birdMove new:))
		(ethelMac init: setScript: (birdMove new:))
		(cacique init: setScript: (birdMove new:))
		(maBird setLoop: 2 init:)
		(paBird setLoop: 1 init:)
		(egg x: 254 init: setScript: eggScript)
		(burner setLoop: 0 setCycle: Forward init:)
		(boaBody init:)
		(boa init: setCel: 0)
		(super init: &rest)
		(self setScript: demoRm110Scr)
	)
	
	(method (dispose)
		(theMusic fade:)
		(super dispose:)
	)
	
	(method (cue)
		(theMusic number: 200 loop: -1 play: 70 fade: 127 5 10 0)
	)
)

(instance demoRm110Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1 (= ticks 120))
			(2
				(theText init:)
				(= ticks 200)
			)
			(3
				(theText dispose:)
				(= ticks 60)
			)
			(4
				(theText cel: 1 init:)
				(= ticks 200)
			)
			(5
				(theText dispose:)
				(= ticks 240)
			)
			(6
				(ego
					setPri: 1
					view: 6
					setLoop: 6
					posn: 146 195
					scaleX: 110
					scaleY: 110
					scaleSignal: (| (ego scaleSignal?) noStepScale)
					setScale:
					yStep: 4
					init:
					setSpeed: 8
					setCycle: Forward
					setMotion: MoveTo 145 159 self
				)
			)
			(7
				(ego
					view: 7
					loop: 7
					setCel: 0
					posn: 146 178
					yStep: 4
					scaleSignal: 1
					setCycle: EndLoop self
				)
			)
			(8
				(ego
					posn: 132 138
					normalize: 0
					setPri: 12
					setHeading: 45 self
				)
			)
			(9
				(maBird setScript: maSquawker self)
				(paBird setScript: paSquawker)
			)
			(10
				(if
					(or
						(!= (maSquawk prevSignal?) -1)
						(!= (paSquawk prevSignal?) -1)
					)
					(-- state)
				)
				(= ticks 5)
			)
			(11
				(messager say: 0 0 3 0 self 105)
			)
			(12
				(self setScript: walkToSnake self)
			)
			(13 (curRoom newRoom: 120))
		)
	)
)

(instance birdMove of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				(>= (theGame detailLevel:) (client detailLevel:))
					(client
						cycleSpeed: (Random 4 12)
						setCycle: (if (Random 0 1) Forward else Reverse)
					)
				)
				(= ticks (Random 30 60))
			)
			(1
				(client setCycle: 0 stopUpd:)
				(= seconds (Random 2 6))
			)
			(2 (self changeState: 0))
		)
	)
)

(instance maSquawker of Script
	(properties)
	
	(method (init)
		(= register 1)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register
					(= cycles 1)
				else
					(= seconds (Random 4 8))
				)
			)
			(1
				(maSquawk number: 241 loop: -1 play:)
				(= ticks 5)
				(= register (Random 2 3))
			)
			(2
				(client cel: 0 setCycle: EndLoop self)
			)
			(3 (= cycles 2))
			(4
				(if (-- register) (= state (- state 3)))
				(= cycles 2)
			)
			(5
				(maSquawk stop:)
				(client stopUpd:)
				(if caller
					(if local227
						(paSquawker caller: caller)
					else
						(caller cue:)
					)
					(= caller 0)
				)
				(= register 0)
				(self changeState: 0)
			)
		)
	)
)

(instance paSquawker of Script
	(properties)
	
	(method (init)
		(= register 1)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register
					(= cycles 1)
				else
					(= seconds (Random 4 8))
				)
			)
			(1
				(paSquawk number: 240 loop: -1 play:)
				(= ticks 5)
				(= register (Random 2 3))
			)
			(2 (client setCycle: EndLoop self))
			(3 (= cycles 2))
			(4 (client setCycle: BegLoop self))
			(5
				(if (-- register) (= state (- state 4)))
				(= cycles 2)
			)
			(6
				(= register (= local227 0))
				(paSquawk stop:)
				(client stopUpd:)
				(if caller (caller cue:) (= caller 0))
				(self changeState: 0)
			)
		)
	)
)

(instance eggScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				(>= (theGame detailLevel:) (client detailLevel:))
					(client
						setLoop: 3
						cycleSpeed: 18
						setCycle: (if (Random 0 1) EndLoop else BegLoop) self
					)
				else
					(self cue:)
				)
			)
			(1 (= seconds (Random 5 10)))
			(2 (self changeState: 0))
		)
	)
)

(instance broScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				(>= (theGame detailLevel:) (client detailLevel:))
					(client setLoop: 5 setCel: 0 setCycle: Oscillate 1 self)
				else
					(self cue:)
				)
			)
			(1
				(client stopUpd:)
				(= seconds (Random 3 8))
			)
			(2 (self changeState: 0))
		)
	)
)

(instance eatIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: EndLoop self))
			(1
				(if (not (-- local0))
					(= local0 (Random 3 6))
					(client setCel: 0)
					(self changeState: 0)
				else
					(client setCel: (if (client cel?) 0 else 255))
					(= cycles (client cycleSpeed?))
				)
			)
			(2
				(= state (- state 2))
				(self cue:)
			)
		)
	)
)

(instance hyacinth of Prop
	(properties
		x 238
		y 178
		view 240
		loop 12
		priority 12
		signal (| ignrAct fixPriOn stopUpdOn)
		cycleSpeed 10
		detailLevel 2
	)
)

(instance cacique of Prop
	(properties
		x 216
		y 183
		view 240
		loop 9
		cel 1
		signal (| ignrAct stopUpdOn)
		cycleSpeed 15
		detailLevel 2
	)
)

(instance rickyMac of Prop
	(properties
		x 98
		y 151
		view 240
		loop 5
		signal (| ignrAct stopUpdOn)
		cycleSpeed 12
		detailLevel 4
	)
)

(instance fredMac of Prop
	(properties
		x 35
		y 75
		view 240
		loop 14
		signal (| ignrAct stopUpdOn)
		cycleSpeed 12
		detailLevel 3
	)
)

(instance lucyMac of Prop
	(properties
		x 20
		y 11
		view 240
		loop 15
		signal (| ignrAct stopUpdOn)
		detailLevel 4
	)
)

(instance ethelMac of Prop
	(properties
		x 36
		y 141
		view 240
		loop 4
		priority 15
		signal (| ignrAct fixPriOn stopUpdOn)
		detailLevel 5
	)
)

(instance sloth of Prop
	(properties
		x 253
		y 142
		view 240
		loop 3
		cel 1
		signal ignrAct
		cycleSpeed 10
		detailLevel 3
	)
)

(instance boaBody of Prop
	(properties
		x 55
		y 47
		view 250
		signal (| ignrAct stopUpdOn)
	)
)

(instance boa of Prop
	(properties
		x 74
		y 45
		view 250
		loop 1
		signal (| ignrAct stopUpdOn)
	)
)

(instance egg of Prop
	(properties
		x 254
		y 80
		view 241
		loop 3
		signal (| ignrAct stopUpdOn)
		detailLevel 4
	)
)

(instance burner of Prop
	(properties
		x 272
		y 60
		view 240
		priority 1
		signal fixPriOn
		detailLevel 2
	)
)

(instance maBird of Prop
	(properties
		x 251
		y 74
		view 241
		loop 2
		priority 1
		signal fixPriOn
		detailLevel 2
	)
)

(instance paBird of Prop
	(properties
		x 278
		y 77
		view 241
		loop 1
		priority 2
		signal fixPriOn
		detailLevel 2
	)
)

(instance broBird of Prop
	(properties
		x 192
		y 16
		view 241
		loop 5
		cel 2
		signal (| ignrAct stopUpdOn)
		detailLevel 3
	)
)

(instance walkToSnake of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 205
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 158 140 self
				)
			)
			(1
				(ego normalize: 0 setHeading: 0 self)
			)
			(2
				(ego
					view: 7
					setLoop: 3
					setCel: 0
					setPri: 12
					setCycle: EndLoop self
				)
			)
			(3
				(ego
					view: 6
					setCel: 0
					posn: 158 116
					scaleSignal: (| (ego scaleSignal?) $0004)
					yStep: 4
					setCycle: Forward
					setMotion: MoveTo 156 67 self
				)
			)
			(4
				(ego
					view: 7
					setLoop: 7
					setCel: 0
					posn: 158 90
					setCycle: EndLoop self
				)
			)
			(5
				(ego normalize: 0 setPri: 12 posn: 143 51)
				(= cycles 2)
			)
			(6 (= ticks 10))
			(7 (ego setHeading: 270 self))
			(8 (= ticks 45))
			(9
				(ego
					view: 205
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 125 55 self
				)
			)
			(10
				(boa setCycle: EndLoop self)
				(soundFx number: 245 loop: 1 play:)
			)
			(11
				(ego
					view: 2
					setLoop: 5
					setCel: 0
					cycleSpeed: 10
					setCycle: CycleTo 2 1
				)
				(= ticks 60)
			)
			(12
				(ego
					cycleSpeed: 9
					view: 12
					loop: 3
					cel: 2
					posn: 140 76
					setCycle: MoveCycle @walkToSnakeCycle self
				)
			)
			(13 (ego hide:) (= cycles 2))
			(14 (self dispose:))
		)
	)
)

(instance theText of View
	(properties
		x 27
		y 160
		view 93
		loop 1
		priority 15
		signal fixPriOn
	)
)

(instance maSquawk of Sound)

(instance paSquawk of Sound)
