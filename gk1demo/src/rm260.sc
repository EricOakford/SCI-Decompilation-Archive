;;; Sierra Script 1.0 - (do not remove this comment)
(script# 260)
(include sci.sh)
(use Main)
(use Procs)
(use GKEgo)
(use RangeOsc)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use StopWalk)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm260 0
)

(local
	local0
)
(instance rm260 of Rm
	(properties
		noun 1
		picture 260
		style $000a
	)
	
	(method (init)
		(super init:)
		(Load rsMESSAGE 260)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						104
						142
						120
						153
						235
						151
						247
						139
						301
						133
						299
						126
						236
						127
						229
						120
						109
						122
						89
						131
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 166 129 232 128 242 141 215 142 167 141
					yourself:
				)
		)
		(pole init:)
		(backWall init:)
		(balcony init:)
		(candles init:)
		(counter init:)
		(drums init:)
		(theFlags init:)
		(bigUglyMask init:)
		(hornedDoll init:)
		(curlyKnife init:)
		(donationStuff init:)
		(mariePic init:)
		(sitSkeleton init:)
		(hatSkeleton init:)
		(whip init:)
		(theWindow init:)
		(stump init:)
		(gourd init:)
		(doorWay init:)
		(fanSwitch init:)
		(coffin init:)
		(theGirl init:)
		(flame init:)
		(maskReflection init:)
		(headReflection init:)
		(snake init:)
		(drummer init:)
		(fan init:)
		(walkHandler add: doorWay)
		(walkHandler add: balcony)
		(Load rsVIEW 260)
		(Load rsVIEW 2601)
		(Load rsVIEW 265)
		(curRoom setScript: enterEgo)
	)
	
	(method (dispose)
		(walkHandler delete: doorWay)
		(walkHandler delete: balcony)
		(super dispose:)
		(DisposeScript 939)
		(DisposeScript 938)
		(DisposeScript 956)
	)
)

(instance enterEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic1
					number: 260
					loop: -1
					play:
					setVol: 0
					fade: 80 25 10 0
				)
				(ego
					view: 900
					loop: 0
					cel: 0
					setCycle: StopWalk -1
					init:
					state: 2
					ignoreControl: -32768
					ignoreActors: 1
					signal: 4096
					posn: 65 122
					setMotion: MoveTo 117 122
				)
				(= cycles 15)
			)
			(1
				(if (== currentDay 1)
					(messager say: 26 0 1 1 self)
					(theGirl loop: 5 cel: 0 setCycle: Osc 1 self)
				)
			)
			(2)
			(3
				(messager say: 26 0 1 2 self)
			)
			(4
				(theGirl setScript: doRandomStuff)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance leaveTheRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 109 122 self)
			)
			(1
				(theMusic1 fade: 0 10 25 1)
				(ego setMotion: MoveTo 65 122 self)
			)
			(2
				(curRoom newRoom: 200)
				(self dispose:)
			)
		)
	)
)

(instance doRandomStuff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGirl loop: 1 cel: 0 setCycle: Osc 1 self)
			)
			(1 (= seconds (Random 2 5)))
			(2
				(theGirl loop: 2 cel: 0 setCycle: Osc 1 self)
			)
			(3 (= seconds (Random 2 5)))
			(4
				(theGirl loop: 3 cel: 0 setCycle: Osc 1 self)
			)
			(5 (= seconds (Random 2 5)))
			(6
				(theGirl loop: 4 cel: 0 setCycle: Osc 1 self)
			)
			(7 (= seconds (Random 2 5)))
			(8 (self init:))
		)
	)
)

(instance interrogateTheGirl of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 253 127 self)
			)
			(1
				(doRandomStuff dispose:)
				(Face ego theGirl self)
			)
			(2
				(messager say: 24 10 2 1 self)
			)
			(3
				(messager say: 24 10 2 2 self)
				(theGirl loop: 5 cel: 0 setCycle: Osc 1 self)
			)
			(4)
			(5
				(messager say: 24 10 2 3 self)
				(theGirl loop: 5 cel: 0 setCycle: Osc 1 self)
			)
			(6)
			(7
				(messager say: 24 10 2 4 self)
			)
			(8
				(theGame handsOn:)
				(theGirl setScript: doRandomStuff)
				(self dispose:)
			)
		)
	)
)

(instance showSomethingToGirl of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 253 127 self)
			)
			(1
				(doRandomStuff dispose:)
				(Face ego theGirl self)
			)
			(2
				(messager say: 24 0 0 1 self)
				(ego view: 904 loop: 4 cel: 0 setCycle: End self)
			)
			(3)
			(4
				(messager say: 24 0 0 2 self)
				(theGirl loop: 5 cel: 0 setCycle: Osc 1 self)
			)
			(5)
			(6 (ego setCycle: Beg self))
			(7
				(GKEgo normalize: 6 900)
				(theGame handsOn:)
				(theGirl setScript: doRandomStuff)
				(self dispose:)
			)
		)
	)
)

(instance talkToTheGirl of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
				(not (if (== (ego x?) 253) (== (ego y?) 127)))
					(ego setMotion: PolyPath 253 127 self)
				else
					(= cycles 1)
				)
			)
			(1
				(doRandomStuff dispose:)
				(Face ego theGirl self)
			)
			(2
				(messager say: 24 11 0 1 self)
			)
			(3
				(messager say: 24 11 0 2 self)
				(theGirl loop: 5 cel: 0 setCycle: Osc 1 self)
			)
			(4)
			(5
				(theGame handsOn:)
				(theGirl setScript: doRandomStuff)
				(self dispose:)
			)
		)
	)
)

(instance moveSnake of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(snake view: 260 loop: 3 cel: 0 setCycle: End self)
			)
			(1
				(snake stopUpd:)
				(= seconds (Random 3 7))
			)
			(2 (self init:))
		)
	)
)

(instance cycleTheDrummer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(drummer
					loop: 4
					setCycle: ForwardCounter (Random 5 8) self
				)
			)
			(1
				(drummer loop: 5 cel: 0 stopUpd:)
				(= seconds (Random 3 7))
			)
			(2 (self init:))
		)
	)
)

(instance flipTheSwitch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 98 128 self)
			)
			(1
				(messager say: 28 8 8 1)
				(ego view: 265 loop: 0 cel: 0 setCycle: End self)
			)
			(2
				(moveSnake dispose:)
				(theSound1 number: 2670 play: self)
				(fan cycleSpeed: 9 setCycle: End)
				(ego loop: 1 cel: 0 setCycle: End)
			)
			(3
				(theSound1 number: 2601 loop: -1 play:)
				(theMusic2 number: 262 setLoop: -1 play:)
				(snake view: 265 loop: 4 cel: 0 setCycle: Fwd)
				(fan loop: 1 cel: 0 cycleSpeed: 9 setCycle: Fwd)
				(= cycles 1)
				(doRandomStuff dispose:)
			)
			(4
				(messager say: 28 8 8 2)
				(theGirl loop: 6 cel: 0 setCycle: End self)
			)
			(5
				(ego loop: 2 cel: 0 setCycle: End self)
			)
			(6 (= seconds 3))
			(7
				(messager say: 28 8 8 3)
				(ego loop: 3 cel: 0 setCycle: End self)
			)
			(8
				(ego loop: 0 cel: 0 setCycle: End self)
			)
			(9
				(theSound1 number: 2671 loop: 1 play:)
				(snake
					view: 265
					loop: 4
					cel: 0
					cycleSpeed: 11
					setCycle: Osc 1
				)
				(fan loop: 0 cel: 12 cycleSpeed: 9 setCycle: Beg self)
				(ego loop: 1 cel: 0 setCycle: End self)
				(messager say: 28 8 8 4)
				(theMusic2 number: 262 setLoop: 1 play:)
			)
			(10)
			(11
				(theGirl cel: 6 setCycle: Beg self)
			)
			(12
				(theGame handsOn:)
				(snake setScript: moveSnake)
				(messager say: 28 8 8 5)
				(theGirl setScript: doRandomStuff)
				(ego normalize: 3 900)
				(self dispose:)
			)
		)
	)
)

(instance lookAtTheSnake of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 299 127 self)
			)
			(1
				(Face ego snake self)
				(if (not local0) (doRandomStuff dispose:))
			)
			(2
				(if local0
					(messager say: 9 4 20 1 self)
				else
					(theGirl loop: 4 cel: 0 setCycle: End self)
				)
			)
			(3
				(messager say: 9 4 21 1 self)
				(theGirl loop: 7 cel: 0 setCycle: Fwd)
			)
			(4
				(theGirl loop: 4 cel: 2 setCycle: Beg self)
			)
			(5
				(Face ego donationStuff self)
			)
			(6
				(if local0
					(messager say: 9 4 20 2 self)
				else
					(messager say: 9 4 21 2 self)
				)
			)
			(7
				(theGame handsOn:)
				(ego normalize:)
				(if (not local0) (theGirl setScript: doRandomStuff))
				(self dispose:)
			)
		)
	)
)

(instance tryToPickUpSnake of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 299 127 self)
			)
			(1
				(messager say: 9 12 21 1 self)
				(Face ego snake self)
				(if (not local0) (doRandomStuff dispose:))
			)
			(2)
			(3
				(if local0
					(messager say: 9 12 20 1 self)
				else
					(theGirl loop: 4 cel: 0 setCycle: End self)
				)
			)
			(4
				(messager say: 9 12 21 2 self)
				(theGirl loop: 7 cel: 0 setCycle: Osc 1 self)
			)
			(5)
			(6
				(theGirl loop: 4 cel: 2 setCycle: Beg self)
			)
			(7
				(theGame handsOn:)
				(if (not local0) (theGirl setScript: doRandomStuff))
				(ego normalize:)
				(self dispose:)
			)
		)
	)
)

(instance playTheDrums of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
				(not (if (== (ego x?) 268) (== (ego y?) 136)))
					(ego setMotion: PolyPath 268 136 self)
				else
					(= cycles 1)
				)
			)
			(1
				(Face ego 268 199 self)
				(if (not local0) (doRandomStuff dispose:))
			)
			(2
				(if local0
					(messager say: 8 8 20 1 self)
				else
					(messager say: 8 8 21 1 self)
					(theGirl loop: 5 cel: 0 setCycle: Osc 1 self)
				)
			)
			(3)
			(4
				(if local0 (Face ego counter self) else (self cue:))
			)
			(5
				(if local0
					(messager say: 8 8 20 2 3 self)
				else
					(theGame handsOn:)
					(theGirl setScript: doRandomStuff)
					(self dispose:)
				)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance openTheCoffin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Load rsAUDIO 2603)
				(if
				(not (if (== (ego x?) 98) (== (ego y?) 128)))
					(ego setMotion: PolyPath 98 128 self)
				else
					(self cue:)
				)
			)
			(1
				(messager say: 10 6 15 1 self)
				(doRandomStuff dispose:)
				(theGirl loop: 4 cel: 0 setCycle: End self)
			)
			(2)
			(3
				(messager say: 10 6 15 2 self)
				(theGirl loop: 5 setCycle: ROsc 3 4 5)
			)
			(4
				(ego view: 262 loop: 0 cel: 0 setCycle: CT 1 1 self)
			)
			(5
				(theSound1 number: 2603 play:)
				(ego view: 262 loop: 0 cel: 2 setCycle: End self)
			)
			(6
				(messager say: 10 6 15 3 self)
			)
			(7
				(messager say: 10 6 15 4 self)
				(theGirl loop: 5 setCycle: ROsc 3 4 5)
			)
			(8
				(theSound1 number: 2604 play:)
				(ego setCycle: Beg self)
			)
			(9
				(theGame handsOn:)
				(Bset 112)
				(theGirl setScript: doRandomStuff)
				(ego normalize: 1 900)
				(self dispose:)
			)
		)
	)
)

(instance openTheCoffinAgain of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if
				(not (if (== (ego x?) 98) (== (ego y?) 128)))
					(ego setMotion: PolyPath 98 128 self)
				else
					(self cue:)
				)
			)
			(1
				(theSound1 number: 2603 play:)
				(ego view: 262 loop: 0 cel: 0 setCycle: End self)
			)
			(2
				(messager say: 10 6 16 0 self)
			)
			(3
				(ego setCycle: Beg self)
				(theSound1 number: 2604 play:)
			)
			(4
				(theGame handsOn:)
				(ego normalize: 1 900)
				(self dispose:)
			)
		)
	)
)

(instance rubTheStump of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (and (!= (ego x?) 166) (!= (ego y?) 137))
					(ego setMotion: PolyPath 166 137 self)
				else
					(self cue:)
				)
			)
			(1
				(ego view: 2621 loop: 0 cel: 0 setCycle: End)
				(= seconds 3)
			)
			(2
				(ego
					view: 2621
					loop: 1
					cel: 0
					setPri: 10
					cycleSpeed: 9
					setCycle: CT 2 1 self
				)
			)
			(3
				(theSound1 number: 2605 play:)
				(ego loop: 1 cel: 3 setCycle: CT 7 1 self)
			)
			(4
				(ego loop: 1 cel: 6 setCycle: End self)
			)
			(5
				(ego normalize: 0 900)
				(if (and (Btst 11) (not (Btst 114)))
					(messager say: 11 8 52 0 self)
					(Bset 114)
				else
					(messager say: 11 8 (Random 45 50) 0 self)
				)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance theGirl of Prop
	(properties
		x 275
		y 96
		noun 24
		sightAngle 20
		view 2601
	)
	
	(method (init)
		(super init:)
		(self setPri: 8)
	)
	
	(method (doVerb theVerb)
		(if
			(or
				(== theVerb 7)
				(== theVerb 9)
				(== theVerb 6)
				(== theVerb 8)
				(== theVerb 12)
			)
			(super doVerb: theVerb)
		else
			(switch theVerb
				(11
					(ego setScript: talkToTheGirl)
				)
				(10
					(ego setScript: interrogateTheGirl)
				)
				(else 
					(ego setScript: showSomethingToGirl)
				)
			)
		)
	)
)

(instance flame of Prop
	(properties
		x 53
		y 107
		noun 5
		view 260
		signal $1000
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 setCycle: Fwd)
	)
)

(instance maskReflection of Prop
	(properties
		x 58
		y 71
		noun 6
		view 260
		loop 1
		signal $1000
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 setCycle: Fwd)
	)
)

(instance headReflection of Prop
	(properties
		x 43
		y 143
		noun 4
		view 260
		loop 2
		signal $1000
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 setCycle: Fwd)
	)
)

(instance snake of Prop
	(properties
		x 292
		y 63
		noun 9
		view 260
		loop 3
		signal $1000
	)
	
	(method (init)
		(super init:)
		(self setScript: moveSnake)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(ego setScript: lookAtTheSnake)
			)
			(3
				(ego setScript: lookAtTheSnake)
			)
			(12
				(ego setScript: tryToPickUpSnake)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fan of Prop
	(properties
		x 126
		y 64
		noun 23
		sightAngle 20
		view 2651
	)
	
	(method (init)
		(super init:)
		(self setPri: 8)
	)
)

(instance drummer of Prop
	(properties
		x 135
		y 97
		noun 27
		sightAngle 20
		view 260
		loop 4
		signal $1000
	)
	
	(method (init)
		(super init:)
		(self setScript: cycleTheDrummer)
	)
)

(instance pole of Feature
	(properties
		x 215
		y 79
		noun 3
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 215 117 211 116 210 113 209 22 221 22 224 137 216 137
					yourself:
				)
		)
		(super init:)
	)
)

(instance backWall of Feature
	(properties
		x 193
		y 61
		noun 19
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 263 48 265 71 301 71 302 88 146 88 144 51
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(messager say: noun theVerb (Random 39 44) 0)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance balcony of Feature
	(properties
		x 164
		y 21
		noun 18
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 310 21 283 40 129 40 100 22
					yourself:
				)
		)
		(super init:)
	)
)

(instance candles of Feature
	(properties
		x 71
		y 123
		noun 5
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						78
						136
						79
						132
						70
						128
						68
						132
						59
						136
						56
						129
						62
						124
						53
						121
						54
						107
						68
						106
						71
						116
						83
						117
						84
						139
						75
						141
					yourself:
				)
		)
		(super init:)
	)
)

(instance counter of Feature
	(properties
		x 266
		y 108
		noun 13
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						233
						98
						239
						99
						265
						100
						266
						97
						283
						98
						299
						102
						288
						108
						284
						116
						280
						125
						239
						125
						233
						117
					yourself:
				)
		)
		(super init:)
	)
)

(instance drums of Feature
	(properties
		x 268
		y 151
		noun 8
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						243
						140
						246
						135
						252
						129
						282
						128
						280
						122
						284
						115
						306
						110
						312
						116
						319
						118
						319
						145
						243
						145
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(ego setScript: playTheDrums)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theFlags of Feature
	(properties
		x 185
		y 38
		noun 12
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						197
						25
						194
						37
						186
						48
						182
						44
						182
						37
						184
						22
						197
						22
						212
						45
						221
						45
						233
						22
						242
						22
						243
						46
						240
						53
						242
						63
						240
						64
						235
						47
						237
						40
						235
						34
						233
						26
						222
						47
						210
						47
					yourself:
				)
		)
		(super init:)
	)
)

(instance bigUglyMask of Feature
	(properties
		x 24
		y 198
		noun 6
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						13
						49
						10
						48
						10
						38
						22
						33
						22
						27
						26
						21
						55
						21
						79
						26
						77
						34
						81
						35
						84
						41
						81
						46
						87
						58
						73
						63
						75
						69
						70
						78
						57
						86
						50
						85
						26
						80
						13
						67
					yourself:
				)
		)
		(super init:)
	)
)

(instance hornedDoll of Feature
	(properties
		x 156
		y 85
		noun 20
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						152
						84
						147
						79
						152
						80
						151
						73
						156
						76
						160
						74
						158
						78
						157
						80
						161
						80
						162
						81
						159
						85
						159
						92
						160
						95
						152
						95
					yourself:
				)
		)
		(super init:)
	)
)

(instance curlyKnife of Feature
	(properties
		x 196
		y 78
		noun 16
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 191 77 206 68 197 82 191 84 189 83
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(if (Btst 46)
					(messager say: noun theVerb 3 0)
				else
					(messager say: noun theVerb 0 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance donationStuff of Feature
	(properties
		x 253
		y 95
		noun 17
		sightAngle 20
		approachX 225
		approachY 121
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 237 93 253 93 254 90 264 91 263 99 238 97
					yourself:
				)
			approachVerbs: 12
		)
		(super init:)
	)
)

(instance mariePic of Feature
	(properties
		x 248
		y 75
		noun 14
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 264 57 262 88 230 87 229 57
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(if (Btst 43)
					(messager say: noun theVerb 36 0)
				else
					(messager say: noun theVerb 37 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sitSkeleton of Feature
	(properties
		x 114
		y 109
		noun 21
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						103
						110
						109
						106
						110
						97
						107
						92
						121
						92
						118
						97
						119
						107
						125
						109
						124
						120
						105
						120
					yourself:
				)
		)
		(super init:)
	)
)

(instance hatSkeleton of Feature
	(properties
		x 30
		y 199
		noun 4
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						0
						90
						9
						85
						27
						85
						33
						92
						36
						99
						50
						99
						54
						101
						55
						105
						48
						112
						51
						122
						55
						121
						60
						124
						60
						127
						49
						134
						51
						138
						63
						137
						69
						128
						80
						132
						74
						141
						76
						145
						0
						145
					yourself:
				)
		)
		(super init:)
	)
)

(instance whip of Feature
	(properties
		x 93
		y 197
		noun 7
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						82
						85
						86
						78
						86
						69
						75
						69
						77
						63
						86
						57
						84
						22
						97
						22
						102
						42
						99
						58
						90
						69
						92
						75
						93
						81
						92
						82
						90
						76
						90
						90
						88
						85
						84
						93
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(if (Btst 46)
					(messager say: noun theVerb 3 0)
				else
					(messager say: noun theVerb 0 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theWindow of Feature
	(properties
		x 126
		y 82
		noun 22
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 113 70 136 70 137 93 115 92
					yourself:
				)
		)
		(super init:)
	)
)

(instance stump of Feature
	(properties
		x 198
		y 125
		noun 11
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 180 122 197 102 216 119 217 137 195 141 178 136
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8 (ego setScript: rubTheStump))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance gourd of Feature
	(properties
		x 311
		y 120
		noun 15
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						287
						110
						298
						103
						299
						97
						305
						89
						319
						86
						319
						118
						308
						115
						304
						109
						293
						116
						288
						116
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(if (Btst 46)
					(messager say: noun theVerb 3 0)
				else
					(messager say: noun theVerb 0 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance doorWay of Feature
	(properties
		x 89
		y 82
		noun 2
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 79 56 99 61 98 103 88 103 87 96 77 96
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(ego setScript: leaveTheRoom)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fanSwitch of Feature
	(properties
		x 69
		y 88
		noun 28
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 66 83 73 83 73 91 68 91
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(if (Btst 110)
					(messager say: noun theVerb 10 0)
				else
					(Bset 110)
					(ego setScript: flipTheSwitch)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance coffin of Feature
	(properties
		x 77
		y 103
		noun 10
		sightAngle 40
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 64 98 72 96 86 97 86 104 79 108 64 107
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(if (Btst 46)
					(messager say: noun theVerb 3 0)
				else
					(messager say: noun theVerb 0 0)
				)
			)
			(6
				(cond 
					((Btst 113) (messager say: noun theVerb 17 0))
					((Btst 112) (ego setScript: openTheCoffinAgain))
					(else (ego setScript: openTheCoffin))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)
