;;; Sierra Script 1.0 - (do not remove this comment)
(script# 900)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use Intrface)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm900 0
)

(instance rm900 of LLRoom
	(properties
		picture 900
		north 905
	)
	
	(method (init)
		(Load SOUND 194)
		(Load SOUND 191)
		(Load SOUND 192)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						127 0
						126 116
						77 116
						77 118
						126 118
						70 156
						55 157
						41 163
						9 165
						9 169
						166 169
						310 169
						309 166
						231 162
						55 162
						55 160
						72 158
						131 118
						230 118
						231 116
						130 116
						130 0
						319 0
						319 189
						0 189
					yourself:
				)
		)
		(ego init: normalize: 552 setStep: 2 2)
		(if (== prevRoomNum 920)
			(InFirstPerson 0)
			(Bset fDentistClosed)
			(self style: IRISOUT)
		)
		(if (not currentCity)
			(= currentCity MIAMI)
		)
		(super init:)
		(if (Btst fLimoHere)
			(limo init: approachVerbs: verbDo)
		)
		(switch prevRoomNum
			(920
				(HandsOn)
				(ego posn: 214 163 0 setHeading: 180 edgeHit: 0)
			)
			(north
				(HandsOn)
				(ego posn: 123 116 0 setHeading: 180 setPri: 4)
				(theMusic number: 0 stop:)
				(theMusic2 number: 0)
			)
			(else 
				(= currentCity MIAMI)
				(Bclr fLimoHere)
				(limo init:)
				(curRoom setScript: sExitLimo)
			)
		)
		(tooth init: cycleSpeed: 6 setCycle: Forward)
		(palmTrees init:)
		(gymWindow init: approachVerbs: verbDo verbLook)
		(gymSign init:)
		(docSign init:)
		(docDoor init: setPri: 1)
		(theDoor init: approachVerbs: verbDo)
		(gymDoor init: approachVerbs: verbDo)
		(building init:)
		(buildings init:)
		(sidewalk init:)
		(fence init:)
		(stairs init:)
		(window1 init: approachVerbs: verbDo verbLook)
		(window2 init: approachVerbs: verbDo verbLook)
		(if (== prevRoomNum 920)
			(HandsOff)
			(StartTimer 3 0 curRoom)
		)
		(theMusic3 number: 251 loop: -1 play:)
		(InFirstPerson 0)
		(SetFFRoom 0)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((and (< (ego x?) 88) (< (ego y?) 130)) 0)
			(
				(and
					(!= (ego priority?) 4)
					(< (ego y?) 162)
					(> (ego y?) 120)
				)
				(ego setPri: 4)
			)
			((and (!= (ego priority?) 2) (< (ego y?) 120))
				(ego setPri: 2)
			)
			((and (== (ego priority?) 4) (> (ego y?) 161))
				(ego setPri: -1)
			)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (< (ego y?) 130)
					(TimePrint 900 1)
					(TimePrint 900 2)
				else
					(TimePrint 900 3)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(TimePrint 900 0)
		(HandsOn)
	)
)

(instance palmTrees of Feature
	(properties
		x 37
		y 101
		nsTop 62
		nsLeft 20
		nsBottom 141
		nsRight 70
		description {the palm trees}
		sightAngle 40
		lookStr {Miami is filled with exotic palm trees just like these.}
	)
)

(instance gymWindow of Feature
	(properties
		x 162
		y 140
		nsTop 127
		nsLeft 140
		nsBottom 150
		nsRight 185
		description {the gymnastics studio}
		sightAngle 40
		approachX 165
		approachY 162
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 900 4)
			)
			(verbDo
				(TimePrint 900 5)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance docSign of Feature
	(properties
		x 182
		y 42
		nsTop 24
		nsLeft 135
		nsBottom 61
		nsRight 225
		description {the sign}
		sightAngle 40
		lookStr {The sign reads, "Doc Pulliam's Dental Hygiene Heaven."}
	)
)

(instance gymDoor of Feature
	(properties
		x 215
		y 146
		nsTop 132
		nsLeft 195
		nsBottom 161
		nsRight 235
		description {the gymnastics studio door}
		sightAngle 40
		approachX 214
		approachY 163
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 900 6)
			)
			(verbDo
				(TimePrint 900 7)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance docDoor of Door
	(properties
		x 114
		y 104
		z -10
		description {the dentist's office door}
		sightAngle 40
		approachX 128
		approachY 116
		lookStr {The door leading to Doc Pulliam's Dental Hygiene Heaven is unlocked.}
		view 900
		loop 1
		priority 1
		entranceTo 905
		moveToX 128
		moveToY 115
		enterType 0
		exitType 0
	)
)

(instance building of Feature
	(properties
		x 157
		y 91
		z -20
		nsTop 60
		nsLeft 65
		nsBottom 162
		nsRight 239
		description {the building}
		sightAngle 40
		lookStr {You are outside the building where Chi Chi Lambada is employed: "Doc Pulliam's Dental Hygiene Heaven." Immediately below it is a closed gymnastics studio, "The Gym-Dandy Gymnastics Center."}
	)
)

(instance sidewalk of Feature
	(properties
		x 169
		y 170
		nsTop 162
		nsBottom 178
		nsRight 319
		description {the sidewalk}
		sightAngle 40
		lookStr {Carefully inspecting the sidewalk, you conclude Doc Pulliam doesn't spend much on filling cavities out here!}
	)
)

(instance fence of Feature
	(properties
		x 164
		y 152
		nsTop 139
		nsBottom 166
		nsRight 308
		description {the fence}
		sightAngle 40
		onMeCheck cGREEN
		lookStr {A stone wall decorates an otherwise perfunctory building.}
	)
)

(instance gymSign of Feature
	(properties
		x 190
		y 119
		nsTop 109
		nsLeft 145
		nsBottom 129
		nsRight 230
		description {the gymnastic studio sign}
		sightAngle 40
		lookStr {The sign says, "Gym-Dandy Gymnastics Center."}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 900 8)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance window1 of Feature
	(properties
		x 184
		y 190
		z 100
		nsTop 78
		nsLeft 145
		nsBottom 102
		nsRight 225
		description {the window}
		sightAngle 40
		approachX 167
		approachY 115
		lookStr {You don't need to peek in those windows!}
	)
)

(instance window2 of Feature
	(properties
		x 92
		y 190
		z 100
		nsTop 78
		nsLeft 89
		nsBottom 100
		nsRight 106
		description {the window}
		sightAngle 40
		approachX 95
		approachY 119
		lookStr {You don't need to peek in those windows!}
	)
)

(instance stairs of Feature
	(properties
		x 101
		y 157
		z 25
		nsTop 106
		nsLeft 70
		nsBottom 158
		nsRight 135
		description {the stairway}
		sightAngle 40
		onMeCheck cCYAN
		lookStr {A flight of stairs leads up to Doc Pulliam's office.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (< (ego y?) 130)
					(ego setMotion: PolyPath 57 164)
				else
					(ego setMotion: PolyPath 126 113)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance tooth of Prop
	(properties
		x 115
		y 64
		nsTop 27
		nsLeft 101
		nsBottom 64
		nsRight 129
		description {the revolving tooth}
		lookStr {Take the Ventura Freeway to the Fontainebleau Freeway to the Eisenhower Turnpike to the Slaussen Cutoff. Get out of your car and cut off your slaussen. Get back in your car and continue on until you reach the Beach Front Highway. Take the Beach Front Highway to 22nd Street Southwest and stop when you come to the giant revolving tooth! You're there! Doc Pulliam's Dental Hygiene Heaven!}
		view 900
		loop 2
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
)

(instance limo of Actor
	(properties
		x 65
		y 179
		description {the limousine}
		approachX 81
		approachY 167
		lookStr {It's your limousine, awaiting your entrance.}
		view 900
		priority 14
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(HandsOff)
				(curRoom setScript: sEnterLimo)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance buildings of Feature
	(properties
		x 159
		y 81
		nsBottom 162
		nsRight 319
		description {the building}
		sightAngle 40
		onMeCheck cBLUE
		lookStr {There are many buildings in a city as large as Miami.}
	)
)

(instance sExitLimo of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego hide: setHeading: 0 posn: 81 177 0)
				(= cycles 2)
			)
			(1
				(theMusic2 number: 191 setLoop: 1 play:)
				(ego
					show:
					setMotion: MoveTo (limo approachX?) (limo approachY?)
				)
				(= ticks 50)
			)
			(2 (= ticks 10))
			(3
				(theMusic2 number: 192 setLoop: 1 play:)
				(= ticks 100)
			)
			(4
				(ego setLoop: -1 setHeading: 180 self)
			)
			(5
				(self setScript: sLimoLeaves self)
			)
			(6
				(HandsOn)
				(ego normalize: 552 setStep: 2 2)
				(limo dispose:)
				(= cycles 1)
			)
			(7
				(self dispose:)
				(if
					(Print 900 9
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
				(= register (theMusic2 flags?))
				(theMusic2 number: 194 setLoop: 1 flags: 0 play:)
			)
			(1
				(limo
					illegalBits: 0
					ignoreActors: 1
					setStep: 3 3
					setMotion: MoveTo (- (limo x?) 20) (limo y?) self
				)
			)
			(2
				(limo
					setStep: 5 5
					setMotion: MoveTo (- (limo x?) 20) (limo y?) self
				)
			)
			(3
				(limo setStep: 8 8 setMotion: MoveTo -80 (limo y?) self)
				(theMusic2 fade: 0 15 12 1 flags: register)
			)
			(4
				(limo dispose:)
				(self dispose:)
			)
		)
	)
)

(instance theMusic3 of Sound)

(instance theDoor of Feature
	(properties
		x 125
		y 99
		nsTop 82
		nsLeft 112
		nsBottom 117
		nsRight 139
		description {the dentist's office door}
		sightAngle 40
		approachX 128
		approachY 116
		lookStr {The door leading to Doc Pulliam's Dental Hygiene Heaven is unlocked.}
	)
	
	(method (doVerb)
		(docDoor doVerb: &rest)
	)
)

(instance sEnterLimo of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 180 self)
			)
			(1
				(ego setMotion: MoveTo 81 177 self)
				(= ticks 10)
			)
			(2
				(theMusic2 number: 191 setLoop: 1 play:)
				(= ticks 50)
			)
			(3 0)
			(4
				(ego hide:)
				(theMusic2 number: 192 setLoop: 1 play:)
				(= ticks 120)
			)
			(5
				(self setScript: sLimoLeaves self)
			)
			(6 (curRoom newRoom: 200))
		)
	)
)
