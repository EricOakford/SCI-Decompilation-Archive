;;; Sierra Script 1.0 - (do not remove this comment)
(script# 160)
(include sci.sh)
(use Main)
(use LBRoom)
(use Conv)
(use Scaler)
(use PolyPath)
(use LoadMany)
(use StopWalk)
(use Motion)
(use Actor)
(use System)

(public
	rm160 0
)

(local
	[local0 41] = [-1 1 0 0 1 0 0 0 -1 1 0 0 2 0 0 0 -1 1 0 0 3 0 0 0 -1 1 0 0 4 0 0 0 -1 1 0 0 5]
	[local41 25] = [-1 1 0 0 8 0 0 0 -1 1 0 0 9 0 0 0 -1 1 0 0 10]
	[local66 17] = [-1 1 0 0 11 0 0 0 -1 1 0 0 12]
)
(instance rm160 of LBRoom
	(properties
		picture 160
		style $000a
	)
	
	(method (init)
		(LoadMany 128 163 826 162 161 160)
		(LoadMany 132 160 161 162 163)
		(ego init: normalize:)
		(self setRegions: 92)
		(super init:)
		(thief init:)
		(theMusic number: 160 flags: 1 loop: -1 play:)
		(self setScript: sCartoon)
	)
	
	(method (dispose)
		(theMusic fade: 0 30 12 1)
		(super dispose: &rest)
	)
)

(instance sCartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 160
					loop: 0
					cel: 0
					x: 137
					y: 160
					setScale: Scaler 100 100 190 0
					setPri: 8
					setCycle: End self
				)
			)
			(1
				(ego loop: 1 cel: 0 x: 95 y: 167 setCycle: End self)
			)
			(2
				(bag init:)
				(ego loop: 2 cel: 0 x: 120 y: 163 setCycle: End self)
			)
			(3
				(trainLady
					init:
					setCycle: Walk
					setScale: Scaler 100 100 190 0
					setMotion: MoveTo 140 125 self
				)
			)
			(4
				(trainLady hide:)
				(ego
					view: 161
					loop: 0
					cel: 0
					posn: 128 167
					setCycle: End self
				)
				((ScriptID 1881 2) modeless: 1)
				((ScriptID 1900 15) modeless: 1)
			)
			(5
				(myConv load: @local0 init: self)
				(thief setScript: sStealIt)
			)
			(6
				(if (thief script?)
					(-- state)
					(= cycles 2)
				else
					(= cycles 2)
				)
			)
			(7
				(theMusic2 number: 162 flags: 1 loop: 1 play:)
				(trainLady
					loop: 1
					posn: 110 160
					show:
					setCycle: Walk
					xStep: 4
					yStep: 3
					setMotion: PolyPath -20 160 self
				)
				(ego
					view: 162
					loop: 2
					cel: 0
					posn: 103 155
					setCycle: CT 4 1
				)
			)
			(8
				(trainLady dispose:)
				(ego setCycle: Beg self)
			)
			(9 (ego setCycle: End self))
			(10
				(messager say: 1 0 0 6 self)
			)
			(11
				(thief
					init:
					view: 162
					loop: 0
					posn: -15 176
					cycleSpeed: 5
					moveSpeed: 5
					setCycle: Walk
					setMotion: MoveTo 6 176 self
				)
			)
			(12
				(messager say: 1 0 0 7 self)
				(theMusic2 number: 163 flags: 1 loop: -1 play:)
			)
			(13
				(ego
					view: 826
					loop: 2
					setCycle: StopWalk -1
					setMotion: PolyPath 37 176 self
				)
				((ScriptID 1881 2) modeless: 1)
				((ScriptID 1901 16) modeless: 1)
			)
			(14
				(thief dispose:)
				(ego
					view: 162
					loop: 1
					cel: 0
					posn: 24 176
					cycleSpeed: 9
					setCycle: CT 5 1
				)
				(myConv load: @local41 init: self)
			)
			(15
				(myConv load: @local66 init: self)
				(theMusic2 number: 164 flags: 1 loop: 1 play: self)
			)
			(16
				(messager say: 1 0 0 13 self)
				(ego
					view: 162
					loop: 1
					posn: 24 176
					cycleSpeed: 9
					setCycle: End self
				)
			)
			(17 0)
			(18 0)
			(19
				(ego cycleSpeed: 6)
				(curRoom newRoom: 180)
				(self dispose:)
			)
		)
	)
)

(instance sStealIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(thief
					setCycle: Walk
					cycleSpeed: 5
					moveSpeed: 5
					setMotion: PolyPath 110 167 self
				)
				(theMusic2 number: 161 flags: 1 loop: 1 play:)
			)
			(1
				(thief loop: 2 cel: 1 posn: 83 168 setCycle: End self)
				(bag dispose:)
			)
			(2
				(thief
					posn: 52 167
					setLoop: 3
					setCycle: Walk
					cycleSpeed: 3
					moveSpeed: 3
					xStep: 4
					yStep: 3
					setMotion: PolyPath -20 167 self
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance trainLady of Actor
	(properties
		x 169
		y 125
		view 161
		loop 1
		signal $4000
	)
)

(instance thief of Actor
	(properties
		x 194
		y 261
		view 163
		loop 1
		signal $4800
		cycleSpeed 8
		moveSpeed 8
	)
	
	(method (cue)
		(super cue:)
		(sCartoon cue:)
	)
)

(instance myConv of Conversation
	(properties)
)

(instance bag of View
	(properties
		x 73
		y 158
		view 160
		loop 3
		signal $4000
	)
)
