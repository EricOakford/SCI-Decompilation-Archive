;;; Sierra Script 1.0 - (do not remove this comment)
(script# 750)
(include game.sh)
(use Main)
(use LLRoom)
(use MoveCyc)
(use LoadMany)
(use Sound)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	rm750 0
)

(local
	[clothesCycles 29] = [0 0 183 137 0 1 220 111 0 2 258 101 0 3 287 117 0 4 319 131 0 4 340 151 0 4 360 151 -32768]
	[local29 33] = [1 0 155 137 1 1 133 115 1 2 104 102 1 3 61 108 1 4 42 119 1 5 12 141 1 5 -5 155 1 5 -35 155 -32768]
	[local62 37] = [2 0 157 143 2 1 177 101 2 2 206 73 2 3 234 54 2 4 264 49 2 5 285 41 2 5 305 35 2 5 325 25 2 5 345 15 -32768]
)
(instance rm750 of LLRoom
	(properties
		picture 750
	)
	
	(method (init)
		(Load SCRIPT 942)
		(LoadMany VIEW 753 754 752)
		(LoadMany SOUND 750 862 751)
		(ego init: x: 125 y: 130 view: 754 setLoop: 0 setCycle: 0)
		(super init:)
		(lana init:)
		(dyke init: stopUpd:)
		(guy1 init: stopUpd:)
		(guy2 init: stopUpd:)
		(guy3 init: stopUpd:)
		(guy4 init: stopUpd:)
		(guy5 init: stopUpd:)
		(guy6 init: stopUpd:)
		(guy7 init: stopUpd:)
		(guy8 init: stopUpd:)
		(self setScript: sCartoon)
		(HandsOff)
	)
)

(instance sCartoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 123))
			(1
				(SolvePuzzle 40 fAfterLana)
				(TimePrint 750 0 67 -1 15 70 280)
				(= ticks 123)
			)
			(2
				(soundFX number: 862 flags: 1 play: guy1)
				(clothes init: setCycle: MoveCycle @local62 self)
			)
			(3
				(clothes hide:)
				(guy8 setCycle: EndLoop self)
			)
			(4
				(guy8 stopUpd:)
				(dyke setCycle: EndLoop self)
			)
			(5
				(dyke stopUpd:)
				(guy7 setCycle: EndLoop self)
			)
			(6
				(guy7 stopUpd:)
				(guy1 setCycle: EndLoop self)
			)
			(7
				(guy1 stopUpd:)
				(soundFX number: 862 play: guy1)
				(clothes show: setCycle: MoveCycle @clothesCycles self)
			)
			(8
				(guy3 setCycle: EndLoop self)
				(clothes hide:)
			)
			(9
				(guy3 stopUpd:)
				(guy2 setCycle: EndLoop self)
			)
			(10
				(guy2 stopUpd:)
				(guy4 setCycle: EndLoop self)
			)
			(11
				(guy4 stopUpd:)
				(soundFX number: 862 play: guy1)
				(clothes show: setCycle: MoveCycle @local29 self)
			)
			(12
				(clothes dispose:)
				(guy5 setCycle: EndLoop self)
			)
			(13
				(guy5 stopUpd:)
				(guy6 setCycle: EndLoop self)
			)
			(14
				(guy6 stopUpd:)
				(guy7 setCycle: EndLoop self)
			)
			(15
				(guy7 stopUpd:)
				(guy8 setCycle: EndLoop self)
			)
			(16
				(guy8 stopUpd:)
				(dyke setCycle: EndLoop self)
			)
			(17
				(dyke stopUpd:)
				(theMusic loop: 1 number: 750 play:)
				(ego setCycle: EndLoop self)
			)
			(18
				(ego stopUpd:)
				(lana setCycle: EndLoop self)
			)
			(19
				(lana stopUpd:)
				(guy1 setCycle: EndLoop self)
			)
			(20
				(guy1 stopUpd:)
				(guy3 setCycle: EndLoop self)
			)
			(21
				(guy3 stopUpd:)
				(ego setLoop: 1 setCel: 0 setCycle: CycleTo 4 1 self)
				(lana setCel: 0 setLoop: 1 setCycle: EndLoop)
			)
			(22 (= seconds 5))
			(23
				(TimePrint 750 1 67 -1 20)
				(ego setCycle: EndLoop self)
			)
			(24
				(ego setLoop: 2 setCel: 0 setCycle: EndLoop self)
				(lana setCel: 0 setLoop: 2 setCycle: EndLoop)
			)
			(25 (= ticks 123))
			(26
				(TimePrint 750 2 80 {You} 67 -1 15 70 280)
				(= ticks 123)
			)
			(27
				(if (>= ((Inventory at: iCamcorder) state?) 100)
					(CheckTapeState 3)
					(SolvePuzzle 20 fRecordedLana)
					(TimePrint 750 3 67 -1 15 70 280)
				else
					(TimePrint 750 4 67 -1 15 70 280)
				)
				(ego setLoop: 3 setCel: 0 setCycle: EndLoop)
				(= seconds 5)
			)
			(28
				(DrawPic 1 IRISIN)
				(cast eachElementDo: #hide)
				(= seconds 3)
			)
			(29
				(TimePrint 750 5)
				(= seconds 3)
			)
			(30 (curRoom newRoom: 700))
		)
	)
)

(instance lana of Prop
	(properties
		x 185
		y 130
		description {Lana}
		sightAngle 40
		lookStr {How Lana has changed! Outside, she was just a beautiful young lady. In here, she's an animal!}
		view 753
		signal ignrAct
	)
)

(instance clothes of Prop
	(properties
		x 150
		y 150
		yStep 13
		view 752
		loop 2
		signal ignrAct
		cycleSpeed 8
	)
)

(instance dyke of Prop
	(properties
		x 143
		y 84
		view 750
		signal ignrAct
	)
)

(instance guy1 of Prop
	(properties
		x 171
		y 81
		view 750
		loop 1
		signal ignrAct
		cycleSpeed 7
	)
	
	(method (cue)
		(super cue:)
		(soundFX number: 751 play:)
	)
)

(instance guy2 of Prop
	(properties
		x 165
		y 46
		view 750
		loop 2
		signal ignrAct
		cycleSpeed 8
	)
)

(instance guy3 of Prop
	(properties
		x 213
		y 45
		view 750
		loop 3
		signal ignrAct
		cycleSpeed 9
	)
)

(instance guy4 of Prop
	(properties
		x 85
		y 64
		view 750
		loop 4
		signal ignrAct
		cycleSpeed 8
	)
)

(instance guy5 of Prop
	(properties
		x 102
		y 82
		view 750
		loop 5
		signal ignrAct
		cycleSpeed 7
	)
)

(instance guy6 of Prop
	(properties
		x 181
		y 59
		view 750
		loop 6
		signal ignrAct
	)
)

(instance guy7 of Prop
	(properties
		x 13
		y 77
		view 750
		loop 7
		signal ignrAct
		cycleSpeed 7
	)
)

(instance guy8 of Prop
	(properties
		x 20
		y 114
		view 750
		loop 8
		signal ignrAct
		cycleSpeed 10
	)
)

(instance soundFX of Sound)
