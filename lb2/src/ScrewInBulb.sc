;;; Sierra Script 1.0 - (do not remove this comment)
(script# 530)
(include sci.sh)
(use Main)
(use LBRoom)
(use Scaler)
(use Osc)
(use PolyPath)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm530 0
)

(instance rm530 of LBRoom
	(properties
		picture 530
		north 600
		south 510
		vanishingX 0
	)
	
	(method (init)
		(LoadMany 128 530 541 542 831)
		(LoadMany 132 540 541 542 543 558)
		(ego init: normalize: 831 setScale: Scaler 105 0 190 0)
		(self setRegions: 90)
		(switch prevRoomNum
			(north
				(Bset 31)
				(ego x: 236 y: 136)
				(self setScript: sUpStairs)
			)
			(south
				(self setScript: sEnterSouth)
			)
			(else 
				(ego posn: 65 180)
				(theGame handsOn:)
			)
		)
		(super init:)
		(if (and (Btst 31) (Btst 33))
			(walkHandler addToFront: wallInside pit rearStairs)
		)
		(if (Btst 31)
			(if (Btst 32)
				(darkPassage init:)
				(lightBulb init: setCel: 1)
			else
				(lightBulb init:)
			)
		else
			(doorSeam init: setOnMeCheck: 1 32)
			(secretDoor addToPic:)
		)
		(windows init: setOnMeCheck: 1 2)
		(stairwellWall init: setOnMeCheck: 1 256)
		(hallWall init: setOnMeCheck: 1 512)
		(floor init: setOnMeCheck: 1 1024)
		(stairs init: setOnMeCheck: 1 4)
		(pit init: setOnMeCheck: 1 8)
		(wallInside init: setOnMeCheck: 1 128)
		(rearStairs init: setOnMeCheck: 1 4096)
		(socket init: setOnMeCheck: 1 16)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego 16384) (self setScript: sExitSouth))
			((IsObjectOnControl ego 8192) (self setScript: sExitSouthWalk))
			((and (IsObjectOnControl ego 8) (Btst 32)) (self setScript: sFallStairs))
		)
	)
	
	(method (dispose)
		(if (Btst 33)
			(walkHandler delete: wallInside pit rearStairs)
		)
		(super dispose:)
	)
)

(instance lightBulb of View
	(properties
		x 162
		y 109
		noun 14
		view 541
		loop 4
		priority 15
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (not cel)
					(messager say: noun theVerb 2)
				else
					(messager say: noun theVerb 1)
				)
			)
			(39
				(if (not cel)
					(curRoom setScript: sReadCarbonPaper)
				else
					(messager say: noun theVerb 4)
				)
			)
			(4
				(cond 
					((or (ego has: 23) (not (Btst 64)) (not cel)) (messager say: noun theVerb 5))
					((not cel) (messager say: noun theVerb 2))
					(else (messager say: noun theVerb 1))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance darkPassage of View
	(properties
		x 111
		y 88
		noun 11
		view 530
		loop 1
		priority 5
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (== (lightBulb cel?) 0)
					(messager say: 11 1 2)
				else
					(messager say: 11 1)
				)
			)
			(8
				(if (== (lightBulb cel?) 0)
					(messager say: 11 8 2)
				else
					(messager say: 11 8)
				)
			)
			(33
				(curRoom setScript: sReplaceLightBulb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance secretDoor of View
	(properties
		x 113
		y 89
		noun 10
		view 530
		priority 13
		signal $4011
	)
)

(instance windows of Feature
	(properties
		y 1
		noun 1
		sightAngle 40
	)
)

(instance stairs of Feature
	(properties
		y 1
		noun 6
		sightAngle 40
	)
	
	(method (initialize)
	)
)

(instance rearStairs of Feature
	(properties
		y 1
		noun 6
		sightAngle 40
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (and (Btst 33) (Btst 31))
					(curRoom setScript: sExitNorth)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pit of Feature
	(properties
		y 1
		noun 7
		sightAngle 40
	)
	
	(method (initialize)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (== (lightBulb cel?) 0)
					(messager say: 7 1 2)
				else
					(messager say: 7 1)
				)
			)
			(3
				(if (and (Btst 33) (Btst 31))
					(curRoom setScript: sExitNorth)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance socket of Feature
	(properties
		y 1
		noun 9
		sightAngle 40
	)
)

(instance doorSeam of Feature
	(properties
		y 1
		noun 5
		sightAngle 40
	)
)

(instance wallInside of Feature
	(properties
		y 1
		noun 8
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (and (Btst 33) (Btst 31))
					(curRoom setScript: sExitNorth)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance stairwellWall of Feature
	(properties
		y 1
		noun 2
		sightAngle 40
	)
)

(instance hallWall of Feature
	(properties
		y 1
		noun 3
		sightAngle 40
	)
)

(instance floor of Feature
	(properties
		y 1
		noun 15
		sightAngle 40
	)
)

(instance sReplaceLightBulb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= register (Sound new:))
				(ego setMotion: PolyPath 155 161 self)
			)
			(1 (ego setHeading: 0 self))
			(2
				(ego
					view: 541
					setLoop: 1
					setCel: 0
					posn: 153 158
					setScale: Scaler 100 100 190 0
					setCycle: CT 3 1 self
				)
			)
			(3
				(ego setCycle: ScrewInBulb 6 register self)
			)
			(4
				(theGame points: 1 167)
				(sFX number: 558 play:)
				(lightBulb setCel: 0)
				(darkPassage hide:)
				(= cycles 1)
			)
			(5 (ego setCycle: End self))
			(6
				((ScriptID 21 1) doit: 792)
				(ego
					normalize: 831
					loop: 6
					cel: 5
					setScale: Scaler 105 0 190 0
					posn: 152 158
				)
				(Bclr 32)
				(Bset 33)
				(ego put: 23)
				(walkHandler addToFront: wallInside pit rearStairs)
				(lightBulb stopUpd:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sReadCarbonPaper of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 155 161 self)
			)
			(1 (ego setHeading: 0 self))
			(2
				(ego
					view: 541
					setLoop: 2
					setCel: 0
					posn: 153 158
					setScale: Scaler 100 100 190 0
					setCycle: End self
				)
			)
			(3
				(messager say: 14 39 3)
				(theGame points: 1 170)
				(= cycles 1)
			)
			(4 (ego setCycle: Beg self))
			(5
				(ego
					setLoop: 6
					setCel: 5
					posn: 152 158
					normalize: 831
					setScale: Scaler 105 0 190 0
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFallStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 167 150 self)
			)
			(1
				(ego
					view: 542
					setLoop: 0
					setCel: 0
					posn: 169 148
					setScale: Scaler 100 100 190 0
					setCycle: End self
				)
			)
			(2
				(cutPassageBottomMask init:)
				(cutFloorMask init:)
				(ego
					setPri: 6
					yStep: 16
					setMotion: MoveTo (ego x?) 183 self
				)
				(sFX number: 542 flags: 1 setLoop: 1 play:)
			)
			(3 (ego hide:) (= ticks 120))
			(4
				(sFX number: 543 play:)
				(= ticks 180)
			)
			(5
				(= deathReason 14)
				(curRoom newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance cutPassageBottomMask of View
	(properties
		x 111
		y 149
		view 530
		loop 2
		priority 14
		signal $4011
	)
)

(instance cutFloorMask of View
	(properties
		x 111
		y 178
		view 530
		loop 3
		priority 15
		signal $4011
	)
)

(instance sUpStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic2 number: 540 loop: -1 flags: 1 play:)
				(= cycles 3)
			)
			(1
				(ego setMotion: MoveTo 103 138 self)
			)
			(2
				(ego setMotion: MoveTo 109 161 self)
			)
			(3
				(theMusic2 fade:)
				(ego setMotion: MoveTo 147 162 self)
			)
			(4
				(WrapMusic pause: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 136 158 self)
			)
			(1
				(WrapMusic pause: 1)
				(theMusic2 number: 541 loop: -1 flags: 1 play:)
				(ego
					view: 541
					setScale: Scaler 100 100 190 0
					setLoop: 0
					setCel: 0
					posn: 127 165
					setPri: 12
					setCycle: End self
				)
			)
			(2
				(ego
					normalize: 831
					setScale: Scaler 105 0 190 0
					posn: 99 161
					setPri: -1
					setMotion: MoveTo 107 140 self
				)
			)
			(3
				(ego setMotion: PolyPath 221 147 self)
			)
			(4
				(ego edgeHit: 1)
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance sExitSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(curRoom newRoom: (curRoom south?))
			)
		)
	)
)

(instance sExitSouthWalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (> (ego x?) 150)
					(ego setMotion: MoveTo 229 148 self)
				else
					(ego setMotion: MoveTo 88 147 self)
				)
			)
			(1
				(curRoom newRoom: (curRoom south?))
			)
		)
	)
)

(instance sEnterSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (> (Random 0 1) 0)
					(ego
						posn: 84 152
						setHeading: 270
						setMotion: MoveTo 58 161 self
					)
				else
					(ego
						posn: 238 155
						setHeading: 90
						setMotion: MoveTo 262 157 self
					)
				)
			)
			(1
				(if (and (Btst 31) (not (Btst 32)))
					(if (Btst 33)
						(theGame handsOn:)
						(self dispose:)
					else
						(theGame handsOn:)
						(= ticks 120)
					)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(2
				(Bset 32)
				(sFX number: 558 play:)
				(lightBulb setCel: 1)
				(darkPassage init:)
				(= ticks 120)
			)
			(3
				(messager say: 12 0 1)
				(self dispose:)
			)
		)
	)
)

(class ScrewInBulb of Osc
	(properties
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		howManyCycles -1
		soundOsc 1
	)
	
	(method (init param1 theHowManyCycles theSoundOsc theCaller)
		(if (>= argc 2)
			(= howManyCycles theHowManyCycles)
			(if (>= argc 3)
				(= soundOsc theSoundOsc)
				(if (>= argc 4) (= caller theCaller))
			)
		)
		(super init: param1 theHowManyCycles theCaller)
	)
	
	(method (doit &tmp screwInBulbNextCel)
		(if
			(or
				(> (= screwInBulbNextCel (self nextCel:)) 4)
				(< screwInBulbNextCel 3)
			)
			(= cycleDir (- cycleDir))
			(self cycleDone:)
		else
			(client cel: screwInBulbNextCel)
		)
	)
	
	(method (cycleDone)
		(soundOsc number: 553 play:)
		(super cycleDone:)
	)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)
