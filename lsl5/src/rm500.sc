;;; Sierra Script 1.0 - (do not remove this comment)
(script# 500)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use Intrface)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm500 0
)

(local
	local0
)
(instance rm500 of LLRoom
	(properties
		picture 500
		north 510
	)
	
	(method (init)
		(switch prevRoomNum
			(north
				(theMusic fade: 80 10 10 0)
				(ego init: normalize: edgeHit: 0)
			)
			(else 
				(theMusic number: 0 stop:)
				(ego
					init:
					normalize:
					posn: 24 177 0
					setLoop: -1
					setHeading: 180
					edgeHit: 0
				)
				(limo init:)
				(= currentCity NEW_YORK)
				(curRoom setScript: sExitLimo)
			)
		)
		(super init:)
		(door init: approachVerbs: verbDo)
		(doorR init: approachVerbs: verbDo)
		(if (or (== prevRoomNum 200) (Btst fLimoHere))
			(limo init: approachVerbs: verbDo)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						145 143
						124 157
						32 157
						32 147
						72 147
						73 145
						3 145
						3 165
						315 165
						315 145
						261 145
						260 147
						295 147
						295 156
						205 156
						190 143
						158 143
						151 0
						319 0
						319 189
						0 189
						0 0
						148 0
					yourself:
				)
		)
		(lavaLamp1 init:)
		(lavaLamp2 init:)
		(plants1 init:)
		(plants2 init:)
	)
	
	(method (doit)
		(super doit:)
		(if (not (mod (++ local0) 10)) (Palette PALCycle 239 254 -1))
		(cond 
			(script)
			((and (IsObjectOnControl ego cBLUE) (not (Btst fTookPissIn500)))
				(HandsOff)
				(curRoom setScript: sPiss)
			)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(verbLook
					(TimePrint 500 0)
					(return TRUE)
				)
				(verbDo
					(cond 
						((== prevRoomNum 200)
							(TimePrint 500 1)
						)
						((Btst fLimoHere)
							(TimePrint 500 2)
						)
						(else
							(TimePrint 500 3)
						)
					)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance sPiss of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SolvePuzzle 1 fTookPissIn500)
				(= seconds 3)
			)
			(1
				(Say ego 500 4)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sExitLimo of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(HandsOff)
				(theMusic2 number: 191 setLoop: 1 play:)
				(= ticks 50)
			)
			(2
				(ego setLoop: 3 setCel: 0 setMotion: MoveTo 24 163 self)
			)
			(3
				(ego setLoop: -1 setHeading: 180)
				(= ticks 10)
			)
			(4
				(theMusic2 number: 192 setLoop: 1 play:)
				(= ticks 110)
			)
			(5
				(WrapMusic firstSound: 500 lastSound: 502)
				(theMusic
					number: 500
					play: WrapMusic
					setVol: 80
					flags: 1
					setLoop: 1
				)
				(self setScript: sLimoLeaves self)
			)
			(6
				(HandsOn)
				(limo dispose:)
				(= cycles 1)
			)
			(7
				(ego normalize:)
				(self dispose:)
				(if
					(Print 500 5
						#button {Save} 1
						#button {Nah, Why Bother?} 0
						#title {AL says}
						#at -1 20
					)
					(theGame save:)
				)
			)
		)
	)
)

(instance sEnterLimo of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setLoop: -1 setHeading: 180 self)
			)
			(1
				(theMusic2 number: 191 setLoop: 1 play:)
				(= ticks 50)
			)
			(2
				(ego setMotion: MoveTo 23 183 self)
			)
			(3
				(ego hide:)
				(= ticks 10)
			)
			(4
				(theMusic2 number: 192 setLoop: 1 play:)
				(= ticks 110)
			)
			(5
				(self setScript: sLimoLeaves self)
			)
			(6
				(curRoom newRoom: 200)
			)
		)
	)
)

(instance sLimoLeaves of Script
	
	(method (doit)
		(super doit:)
		(if (== (theMusic2 prevSignal?) 10)
			(theMusic2 prevSignal: 0)
			(self cue:)
		)
	)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(theMusic2 number: 194 setLoop: 1 play:)
			)
			(1
				(limo setStep: 2 2 setMotion: MoveTo 67 (limo y?) self)
			)
			(2
				(limo setStep: 4 4 setMotion: MoveTo 45 (limo y?) self)
			)
			(3
				(limo setStep: 6 6 setMotion: MoveTo 15 (limo y?) self)
			)
			(4
				(limo setStep: 9 9 setMotion: MoveTo -25 (limo y?) self)
			)
			(5
				(limo
					setStep: 12 12
					setMotion: MoveTo -95 (limo y?) self
				)
			)
			(6
				(self dispose:)
			)
		)
	)
)

(instance limo of Actor
	(properties
		x 75
		y 189
		description {your limousine}
		approachX 23
		approachY 163
		lookStr {Why do all these limos look alike?}
		view 500
		priority 14
		signal $4810
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(HandsOff)
				(theMusic fade:)
				(curRoom setScript: sEnterLimo)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance doorR of Feature
	(properties
		x 188
		y 98
		nsTop 56
		nsLeft 168
		nsBottom 141
		nsRight 209
		description {the front door}
		sightAngle 40
		approachX 154
		approachY 144
		lookStr {If you could figure out how to open this door, you might enter "The Hard Disk Cafe."}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(door open:)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance door of Door
	(properties
		x 146
		y 141
		description {the front door}
		sightAngle 40
		approachX 154
		approachY 144
		lookStr {If you could figure out how to open this door, you might enter "The Hard Disk Cafe."}
		view 500
		loop 2
		entranceTo 510
		moveToX 156
		moveToY 135
		enterType 0
		exitType 0
	)
)

(instance lavaLamp1 of Feature
	(properties
		x 76
		y 97
		nsTop 41
		nsLeft 41
		nsBottom 153
		nsRight 111
		description {the lava lamp}
		sightAngle 40
		onMeCheck cCYAN
		lookStr {Wow! A giant lava lamp! (You've always loved those things!)}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 500 6)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance lavaLamp2 of Feature
	(properties
		x 256
		y 93
		nsTop 33
		nsLeft 220
		nsBottom 154
		nsRight 292
		description {the lava lamp}
		sightAngle 40
		onMeCheck cCYAN
		lookStr {Wow! A giant lava lamp! (You've always loved those things!)}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 500 6)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance plants1 of Feature
	(properties
		x 76
		y 97
		nsTop 41
		nsLeft 41
		nsBottom 153
		nsRight 111
		description {the plant}
		sightAngle 40
		onMeCheck cGREEN
		lookStr {These plants grow here in spite of the atmosphere.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance plants2 of Feature
	(properties
		x 256
		y 93
		nsTop 33
		nsLeft 220
		nsBottom 154
		nsRight 292
		description {the plant}
		sightAngle 40
		onMeCheck cGREEN
		lookStr {These plants grow here in spite of the atmosphere.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)
