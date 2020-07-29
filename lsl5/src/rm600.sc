;;; Sierra Script 1.0 - (do not remove this comment)
(script# 600)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use Intrface)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm600 0
)

(instance rm600 of LLRoom
	(properties
		lookStr {The Shill Building is one of Baltimore's finest office buildings. 
		Its granite, brass and stainless steel facade shines in the sunlight. 
		A revolving door promises access to its interior.}
		north 620
		south 200
	)
	
	(method (init)
		(switch prevRoomNum
			(north
				(self picture: 610)
				(ego
					init:
					normalize: 572
					setStep: 2 1
					posn: 156 177
					setHeading: 180
					edgeHit: 0
				)
				(flagOne init: cycleSpeed: 18 setCycle: Forward)
				(flagTwo init: cycleSpeed: 24 setCycle: Forward)
				(revolvingDoor init: cycleSpeed: 10)
				(limo init: approachVerbs: 3)
				(hydrant init:)
				(car init:)
				(shillSign init:)
				(fReflection init:)
				(theMusic2
					number: 251
					setLoop: -1
					setVol: 127
					flags: mNOPAUSE
					play:
				)
				(HandsOn)
			)
			(south
				(self picture: 610)
				(flagOne init: cycleSpeed: 18 setCycle: Forward)
				(flagTwo init: cycleSpeed: 24 setCycle: Forward)
				(revolvingDoor init: cycleSpeed: 10)
				(limo init: approachVerbs: verbDo)
				(hydrant init:)
				(car init:)
				(shillSign init:)
				(fReflection init:)
				(self setScript: sExitLimo)
			)
			(else 
				(self picture: 600 style: PIXELDISSOLVE)
				(ego
					init:
					normalize: 600
					setCel: 0
					posn: 189 126
					cycleSpeed: 12
				)
				(LoadMany PICTURE 610)
				(LoadMany VIEW 610)
				(LoadMany SOUND 601)
				(theMusic number: 600 flags: mNOPAUSE setLoop: -1 play:)
				(self setScript: sCouple)
			)
		)
		(LoadMany SOUND 611)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 0
						156 0
						156 180
						52 180
						52 182
						264 182
						264 180
						174 180
						174 137
						174 0
						319 0
						319 189
					yourself:
				)
		)
	)
	
	(method (dispose)
		(theMusic fade: 0 15 12 1)
		(theMusic2 fade: 0 15 12 1)
		(super dispose: &rest)
	)
)

(instance sCouple of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: ForwardCounter 2 self)
			)
			(1
				(TimePrint 600 0 #dispose #at -1 20)
				(ego setCycle: Forward)
				(= seconds 5)
			)
			(2
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(ego setLoop: 1 setCel: 0 setCycle: CycleTo 1 1 self)
			)
			(3
				(slap play:)
				(ego setCycle: EndLoop self)
			)
			(4
				(TimePrint 600 1 #at -1 20)
				(theMusic fade: 0 15 12 1)
				(ego dispose:)
				(self setScript: sExitLimo)
			)
		)
	)
)

(instance sExitLimo of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= prevRoomNum (curRoom south?))
					(curRoom drawPic: 610 100)
					(flagOne init: cycleSpeed: 18 setCycle: Forward)
					(flagTwo init: cycleSpeed: 24 setCycle: Forward)
					(revolvingDoor init: cycleSpeed: 10)
					(limo init: approachVerbs: verbDo)
					(hydrant init:)
					(car init:)
					(shillSign init:)
					(fReflection init:)
				)
				(theMusic2
					number: 251
					setLoop: -1
					setVol: 127
					flags: 1
					play:
				)
				(= ticks 90)
			)
			(1
				(theMusic number: 191 setLoop: 1 play:)
				(= ticks 80)
			)
			(2
				(ego
					init:
					setLoop: theStopGroop
					normalize: 572
					setStep: 2 1
					posn: 119 182
					setHeading: 0
					setMotion: MoveTo 119 180 self
				)
			)
			(3 (= ticks 30))
			(4
				(theMusic number: 192 setLoop: 1 play:)
				(= ticks 100)
			)
			(5
				(HandsOn)
				(if (== prevRoomNum (curRoom south?))
					(self dispose:)
				)
				(= seconds 4)
			)
			(6
				(self dispose:)
				(if
					(Print 600 2
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
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 191 setLoop: 1 play:)
				(= ticks 80)
			)
			(1
				((ego looper?) dispose:)
				(ego
					setCycle: 0
					setPri: 9
					setMotion: MoveTo (ego x?) (+ (ego y?) 10) self
				)
			)
			(2 (ego dispose:) (= ticks 30))
			(3
				(theMusic number: 192 setLoop: 1 play:)
				(= ticks 100)
			)
			(4
				(curRoom newRoom: 200)
				(self dispose:)
			)
		)
	)
)

(instance flagOne of Prop
	(properties
		x 98
		y 161
		description {the flag}
		lookStr {The Shill Building's management is so patriotic!}
		view 610
	)
)

(instance flagTwo of Prop
	(properties
		x 220
		y 159
		description {the flag}
		lookStr {The Shill Building's management is so patriotic!}
		view 610
		loop 1
	)
)

(instance revolvingDoor of Door
	(properties
		x 163
		y 178
		description {the revolving door}
		approachX 172
		approachY 179
		lookStr {This revolving door is a highlight of the entrance to the Shill building.}
		view 610
		loop 4
		entranceTo 620
		openSnd 611
		closeSnd 611
		moveToX 165
		moveToY 179
	)
	
	(method (init)
		(if (== prevRoomNum (curRoom north?))
			(self setLoop: 3)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(self setPri: 14 setLoop: 2)
				(super doVerb: theVerb &rest)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(if (== state 0)
			(self setLoop: 4)
			(ego posn: (- approachX 14) (- approachY 1) show:)
		)
	)
	
	(method (open)
		(ego hide: posn: moveToX moveToY)
		(super open:)
	)
)

(instance slap of Sound
	(properties
		number 601
	)
)

(instance limo of Feature
	(properties
		x 109
		y 176
		nsTop 169
		nsLeft 53
		nsBottom 183
		nsRight 166
		description {the limousine}
		approachX 119
		approachY 180
		lookStr {Your limousine waits for your return. Inside, your driver is still smarting from your arrival! You feel sure he has learned his lesson.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(curRoom setScript: sEnterLimo)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance hydrant of Feature
	(properties
		x 21
		y 178
		nsTop 168
		nsLeft 1
		nsBottom 189
		nsRight 41
		description {the fire hydrant}
		lookStr {How do dogs spell relief? "H-Y-D-R-A-N-T"}
	)
)

(instance car of Feature
	(properties
		x 272
		y 168
		nsTop 147
		nsLeft 225
		nsBottom 189
		nsRight 319
		description {the car}
		lookStr {You wonder which Shill Building office drone drives such a junky car?}
	)
)

(instance shillSign of Feature
	(properties
		x 160
		y 133
		nsTop 128
		nsLeft 121
		nsBottom 138
		nsRight 199
		description {the Shill sign}
		sightAngle 40
		lookStr {Patti, it says "SHILL."}
	)
)

(instance fReflection of Feature
	(properties
		x 175
		y 176
		z 107
		nsTop 57
		nsLeft 160
		nsBottom 82
		nsRight 190
		description {the reflection}
		sightAngle 40
		lookStr {Don't stare at the sun, Patti! You'll go blind!}
	)
)
