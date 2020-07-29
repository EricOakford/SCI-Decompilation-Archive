;;; Sierra Script 1.0 - (do not remove this comment)
(script# 160)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use PolyPath)
(use Polygon)
(use Feature)
(use Timer)
(use Sound)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	rm160 0
)

(local
	saveVerbCode
	noIconHelp
	noDoHelp
	noLookHelp
	coolerBelched
)
(instance rm160 of LLRoom
	(properties
		lookStr {Once PornProdCorp was heavily involved in X-rated movies, but since their recent I.P.O. and entry into soft-R television, 
		they've totally changed their image. Now they are struggling their way into respectability.}
		picture 160
		north 180
		east 190
		west 170
	)
	
	(method (init)
		(self setRegions: rgHollywood)
		(ego init: normalize: illegalBits: cWHITE)
		(switch prevRoomNum
			(140
				(HandsOn)
				(theMusic number: 160 setLoop: -1 play:)
				(= style IRISOUT)
				(ego posn: 138 121 setHeading: 180 edgeHit: 0)
				(theIconBar disable: 8)
			)
			(155
				(Bset fAfterCoffee)
				(= transferRoom 160)
				(SetFFRoom 0)
				(ego get: iCamcorder)
				(= score 2)
				(if (Btst fFlag80)
					(+= score 1)
				)
				(if (Btst fLookAtBronzeAward)
					(+= score 1)
				)
				(theMusic number: 160 setLoop: -1 play:)
				(HandsOn)
			)
			(west
				(self setScript: sFromWest)
			)
			(north
				(HandsOn)
				(ego
					posn: 260 77
					setHeading: 180
					edgeHit: 0
					illegalBits: 0
				)
			)
			(east (ego y: 160))
			(else 
				(if (!= prevRoomNum 150)
					(Bset fAfterCoffee)
					(= score 2)
					(if (Btst fFlag80)
						(+= score 1)
					)
					(if (Btst fLookAtBronzeAward)
						(+= score 1)
					)
					(ego get: iCamcorder)
				)
				(ego
					posn: (presDoor approachX?) (presDoor approachY?)
					edgeHit: 0
				)
				(theMusic number: 160 setLoop: -1 play:)
				(HandsOn)
			)
		)
		(super init:)
		(fileDoor init: approachVerbs: verbDo)
		(presDoor init: approachVerbs: verbDo)
		(if (not (Btst fAfterCoffee))
			(coffee init: approachVerbs: verbDo setScript: sCoffee)
		)
		(coffeeMaker init:)
		(leftDoor init: approachVerbs: verbDo)
		(bigSign init:)
		(portrait init: approachVerbs: verbDo verbLook)
		(poster init: approachVerbs: verbDo verbLook)
		(pictures init: approachVerbs: verbDo verbLook)
		(onePicture init: approachVerbs: verbDo verbLook)
		(certificate init: approachVerbs: verbDo verbLook)
		(plant init: approachVerbs: verbDo)
		(smallPlant init: approachVerbs: verbDo)
		(largePlant init: approachVerbs: verbDo)
		(bronzeAward init: approachVerbs: verbDo verbLook)
		(waterCooler init: approachVerbs: verbDo)
		(chair init:)
		(desk init: approachVerbs: verbDo)
		(cabinetDoors init: approachVerbs: verbDo verbLook)
		(outlet init: approachVerbs: verbDo verbLook)
		(hallway init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 79
						296 79
						286 71
						273 71
						273 44
						250 44
						250 71
						233 71
						219 86
						143 86
						137 74
						118 74
						104 59
						0 59
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 73
						79 73
						99 76
						98 92
						66 108
						54 113
						12 113
						11 119
						60 119
						48 131
						22 135
						2 148
						2 187
						185 187
						190 181
						216 181
						220 187
						314 187
						319 157
						319 189
						0 189
					yourself:
				)
		)
		(if (not (Btst fAfterCoffee))
			(helpTimer set: helpTimer 45)
		)
		(= saveVerbCode doVerbCode)
		(= doVerbCode doVerb160Code)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego cBLUE)
				(if (not (Btst fAfterCoffee))
					(self setScript: sMoveOffControl 0 10)
				else
					(self newRoom: west)
				)
			)
			((and (IsObjectOnControl ego 4) (not (Btst fAfterCoffee)))
				(self setScript: sMoveOffControl 0 -10)
			)
			(
				(and
					(not coolerBelched)
					(== (ego loop?) 3)
					(< 30 (ego distanceTo: waterCooler))
					(< (ego distanceTo: waterCooler) 40)
				)
				(self setScript: sBelch)
			)
			((and coolerBelched (> (ego distanceTo: waterCooler) 60))
				(= coolerBelched FALSE)
			)
		)
	)
	
	(method (dispose)
		(helpTimer dispose: delete:)
		(coffeeTimer dispose: delete:)
		(= doVerbCode saveVerbCode)
		(super dispose:)
	)
	
	(method (newRoom n)
		(if (== n 190)
			(theMusic fade: 80 25 10 0)
		)
		(super newRoom: n)
	)
)

(instance sMoveOffControl of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setMotion: MoveTo (+ (ego x?) register) (ego y?) self
				)
			)
			(1
				(TimePrint 160 0)
				(self dispose:)
			)
		)
	)
)

(instance sCoffee of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(coffeeTimer cycleCnt: 0)
				(coffeeYell
					init:
					setCel: 0
					posn: (coffeeYell x?) (Random 53 104)
					cycleSpeed: (Random 5 10)
					setCycle: EndLoop self
				)
			)
			(1
				(coffeeSound setLoop: 0 play:)
				(coffeeYell setCycle: CycleTo 3 -1 self)
			)
			(2
				(coffeeYell setCycle: EndLoop)
				(= ticks (Random 30 90))
			)
			(3
				(coffeeYell dispose:)
				(coffeeTimer setReal: coffeeTimer (Random 6 12))
				(self dispose:)
			)
		)
	)
)

(instance sFromWest of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 65 70 setMotion: MoveTo 95 70 self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sBelch of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(waterSound setLoop: 0 play:)
				(waterCooler setLoop: 4 setCycle: EndLoop self)
			)
			(1
				(waterCooler setLoop: 3 setCel: 0)
				(ego
					view: 160
					loop: 5
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2
				(Say ego 160 1 #at -1 185)
				(ego setLoop: 3 normalize: illegalBits: cWHITE)
				(HandsOn)
				(= coolerBelched TRUE)
				(self dispose:)
			)
		)
	)
)

(instance sDeliver of Script
	
	(method (changeState newState &tmp xTo yTo)
		(switch (= state newState)
			(0
				(HandsOff)
				(coffeeTimer dispose: delete:)
				(helpTimer dispose: delete:)
				(ego view: 141 setLoop: 0 setCycle: CycleTo 1 1 self)
			)
			(1
				(coffee dispose:)
				(ego setCycle: EndLoop self)
			)
			(2
				(coffee dispose:)
				(if (cast contains: coffeeYell) (coffeeYell dispose:))
				(SolvePuzzle 1 fGetCoffee)
				(TimePrint 160 2)
				(= xTo (presDoor approachX?))
				(= yTo (presDoor approachY?))
				(ego
					normalize: 141
					setLoop: 1
					setCycle: Walk
					setMotion: PolyPath xTo yTo self
				)
			)
			(3
				(presDoor entranceTo: 140 doVerb: 3)
			)
		)
	)
)

(instance fileDoor of Door
	(properties
		x 245
		y 67
		description {the file room door}
		approachX 264
		approachY 76
		view 160
		loop 2
		entranceTo 180
		moveToX 264
		moveToY 61
		enterType 0
		exitType 0
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 160 3)
				(= noIconHelp TRUE)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (open)
		(if (not (Btst fAfterCoffee))
			(TimePrint 160 0)
			(= noDoHelp TRUE)
		else
			(ego illegalBits: 0)
			(super open: &rest)
		)
	)
	
	(method (close)
		(ego illegalBits: (| cWHITE cYELLOW))
		(super close:)
	)
)

(instance presDoor of Door
	(properties
		x 47
		y 110
		heading 90
		description {Silas Scruemall's office door}
		approachX 60
		approachY 116
		view 160
		loop 1
		entranceTo 155
		moveToX 27
		moveToY 115
		enterType 1
		exitType 0
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (Btst fAfterCoffee)
					(TimePrint 160 4)
				else
					(super doVerb: theVerb theItem &rest)
				)
				(= noDoHelp TRUE)
			)
			(verbLook
				(TimePrint 160 5)
				(TimePrint 160 6)
				(= noIconHelp TRUE)
			)
			(5
				(Say ego 160 7)
				(TimePrint 160 8 #at -1 185)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (open)
		(if (and (cast contains: coffee) (not (Btst fAfterCoffee)))
			(coffee setScript: sCoffee)
		else
			(super open: &rest)
		)
	)
)

(instance coffee of Prop
	(properties
		x 73
		y 75
		description {the coffee pot}
		approachX 98
		approachY 97
		view 160
		priority 6
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 160 9)
				(= noLookHelp TRUE)
				(= noIconHelp TRUE)
			)
			(verbDo
				(curRoom setScript: sDeliver)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance coffeeYell of Prop
	(properties
		x 32
		y 81
		description {the sound of coffee drinkers}
		lookStr {It looks like someone wants some coffee!}
		view 162
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance coffeeSound of Sound
	(properties
		number 161
	)
)

(instance waterCooler of Prop
	(properties
		x 204
		y 182
		description {the water cooler}
		approachX 201
		approachY 179
		lookStr {The water cooler is the office equipment with which you're most familiar.}
		view 160
		loop 3
		priority 15
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(waterSound play:)
				(self setCycle: EndLoop self)
				(= noDoHelp TRUE)
				(TimePrint 160 10)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(waterCooler setCel: 0)
		(SolvePuzzle 1 80)
	)
)

(instance waterSound of Sound
	(properties
		number 163
	)
)

(instance helpTimer of Timer
	
	(method (cue)
		(cond 
			((not noIconHelp)
				(TimePrint 160 11)
				(TimePrint 160 12)
				(TimePrint 160 13)
				(TimePrint 160 14)
				(self set: self 45)
			)
			((and (== 95 (ego x?)) (== 65 (ego y?)))
				(TimePrint 160 15)
				(TimePrint 160 16)
				(self set: self 45)
			)
			((not noDoHelp)
				(TimePrint 160 17)
				(self set: self 45)
			)
			((not noLookHelp)
				(TimePrint 160 18)
				(self set: self 45)
			)
			(else
				(TimePrint 160 19)
				(TimePrint 160 20)
			)
		)
	)
)

(instance coffeeTimer of Timer

	(method (cue)
		(coffee setScript: sCoffee)
	)
)

(instance coffeeMaker of Feature
	(properties
		x 72
		y 69
		nsTop 61
		nsLeft 62
		nsBottom 78
		nsRight 82
		description {the coffee maker}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (cast contains: coffee)
					(TimePrint 160 21)
				else
					(TimePrint 160 22)
				)
			)
			(verbDo
				(TimePrint 160 23)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance leftDoor of Feature
	(properties
		x 22
		y 87
		nsTop 51
		nsLeft 12
		nsBottom 123
		nsRight 32
		description {Silas Scruemall's office door}
		sightAngle 40
		approachX 60
		approachY 116
	)
	
	(method (doVerb)
		(presDoor doVerb: &rest)
	)
)

(instance bigSign of Feature
	(properties
		x 176
		y 26
		nsLeft 153
		nsBottom 52
		nsRight 199
		description {the "America's Sexiest Home Videos" sign}
		sightAngle 40
		lookStr {Evidently this company is the home of "America's Sexiest Home Videos." You've heard of that show! Especially since you work here!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 160 24)
				(= noDoHelp TRUE)
			)
			(verbTalk
				(TimePrint 160 25)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance portrait of Feature
	(properties
		x 62
		y 40
		nsTop 24
		nsLeft 53
		nsBottom 57
		nsRight 71
		description {the portrait of Silas Scruemall}
		sightAngle 40
		approachX 94
		approachY 95
		lookStr {"Our founder, Silas Scruemall!"}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 160 26)
				(TimePrint 160 27)
				(TimePrint 160 28 67 -1 185)
				(= noDoHelp TRUE)
			)
			(verbTalk
				(TimePrint 160 29)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance poster of Feature
	(properties
		x 93
		y 27
		nsTop 11
		nsLeft 83
		nsBottom 43
		nsRight 103
		description {the poster}
		sightAngle 40
		lookStr {Art is one thing, but this is something else!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 160 30)
				(TimePrint 160 31 67 -1 185)
				(= noDoHelp TRUE)
			)
			(verbTalk
				(TimePrint 160 32)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance pictures of Feature
	(properties
		x 128
		y 29
		nsTop 11
		nsLeft 111
		nsBottom 48
		nsRight 146
		description {the photographs}
		sightAngle 40
		approachX 122
		approachY 75
		lookStr {The pictures are of past stars of PornProdCorp's many films, television shows and artsy documentaries. There are a total of zero pictures of Chief Video Tape Rewinders like you. You have no reason to assume you'll be the first!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 160 33)
				(= noDoHelp TRUE)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance onePicture of Feature
	(properties
		x 220
		y 22
		nsTop 11
		nsLeft 213
		nsBottom 33
		nsRight 228
		description {the hydrant photograph}
		sightAngle 40
		approachX 231
		approachY 78
		lookStr {It's either the star of "Manhunter," or a fire hydrant, you're not sure which.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 160 34)
				(= noDoHelp TRUE)
			)
			(verbTalk
				(TimePrint 160 35)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance certificate of Feature
	(properties
		x 295
		y 24
		nsTop 15
		nsLeft 295
		nsBottom 29
		nsRight 309
		description {the certificate}
		sightAngle 40
		approachX 288
		approachY 76
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 160 36
					#mode teJustCenter
				)
			)
			(verbDo
				(TimePrint 160 37)
				(= noDoHelp TRUE)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance plant of Feature
	(properties
		x 305
		y 45
		nsTop 31
		nsLeft 292
		nsBottom 60
		nsRight 319
		description {the plant}
		sightAngle 40
		approachX 291
		approachY 75
		lookStr {You really enjoy the look of fine plastic.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 160 38)
				(= noDoHelp TRUE)
			)
			(verbTalk
				(TimePrint 160 39)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance smallPlant of Feature
	(properties
		x 13
		y 188
		nsTop 139
		nsBottom 183
		nsRight 26
		description {the plant}
		sightAngle 40
		approachX 16
		approachY 187
		lookStr {You really enjoy the look of fine plastic.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 160 40)
				(= noDoHelp TRUE)
			)
			(verbTalk
				(TimePrint 160 39)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance largePlant of Feature
	(properties
		x 245
		y 180
		nsTop 112
		nsLeft 224
		nsBottom 182
		nsRight 266
		description {the plant}
		sightAngle 40
		approachX 260
		approachY 179
		lookStr {How you love plastic plants! (Especially the smell!)}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 160 41)
				(= noDoHelp TRUE)
			)
			(verbTalk
				(TimePrint 160 39)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance bronzeAward of Feature
	(properties
		x 283
		y 186
		nsTop 173
		nsLeft 280
		nsBottom 189
		nsRight 313
		description {the "Titty" award}
		sightAngle 40
		approachX 283
		approachY 185
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 160 42
					#mode teJustCenter
					#title {Genuine Engraved Plastic}
				)
				(SolvePuzzle 1 81)
				(TimePrint 160 43
					#at -1 185
				)
				(= noIconHelp TRUE)
			)
			(verbDo
				(TimePrint 160 44)
				(= noDoHelp TRUE)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance chair of Feature
	(properties
		x 117
		y 173
		nsTop 164
		nsLeft 106
		nsBottom 183
		nsRight 129
		description {the chair}
		sightAngle 40
		lookStr {This chair and desk belong to the person least in favor with your boss, Silas Scruemall. Currently, they are unoccupied. Often, you sit here.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 160 45)
				(= noDoHelp TRUE)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance desk of Feature
	(properties
		x 132
		y 186
		nsTop 184
		nsLeft 85
		nsBottom 189
		nsRight 179
		description {the desk}
		sightAngle 40
		approachX 151
		approachY 188
		lookStr {This chair and desk belong to the person least in favor with your boss, Silas Scruemall. Currently, they are unoccupied. Often, you sit here.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 160 46)
				(= noDoHelp TRUE)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance cabinetDoors of Feature
	(properties
		x 78
		y 92
		nsTop 86
		nsLeft 70
		nsBottom 99
		nsRight 87
		description {the cabinet}
		sightAngle 40
		lookStr {The only thing in the cabinet under the coffee pot is a box of thoughtfully premeasured, preweakened coffee portions, and some empty plastic bags that used to hold stacks of Styrofoam coffee cups. You have no need for either coffee or filters.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 160 47)
				(= noDoHelp TRUE)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance outlet of Feature
	(properties
		x 51
		y 97
		nsTop 88
		nsLeft 48
		nsBottom 101
		nsRight 55
		description {the electrical outlet}
		sightAngle 40
		approachX 77
		approachY 105
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (== ((Inventory at: iCharger) owner?) curRoom)
					(TimePrint 160 48)
				else
					(TimePrint 160 49)
				)
				(= noIconHelp TRUE)
			)
			(verbDo
				(if (not (== ((Inventory at: iCharger) owner?) curRoom))
					(curRoom setScript: (ScriptID CHARGER 2) 0 1)
				else
					((ScriptID CHARGER 3) doVerb: verbDo)
				)
				(= noDoHelp TRUE)
			)
			(verbUse
				(switch theItem
					(iCharger
						(TimePrint 160 50)
						(curRoom setScript: (ScriptID 22 0))
					)
					(iCamcorder
						(TimePrint 160 51)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance hallway of Feature
	(properties
		x 99
		y 64
		nsTop 57
		nsLeft 77
		nsBottom 71
		nsRight 122
		description {the hallway}
		sightAngle 40
		lookStr {This hallway leads to the Department of Video Tape Rewinding and your workroom, where you fulfill your job as Chief Rewinder and Sterilizer (Betamax Division).}
	)
)

(instance doVerb160Code of Code
	
	(method (doit theVerb theObj &tmp objDesc invDesc [str 100])
		(= objDesc (theObj description?))
		(switch theVerb
			(verbLook
				(if (theObj facingMe: ego)
					(if (theObj lookStr?)
						(TimePrint (theObj lookStr?))
					else
						(WriteDialog theObj theVerb)
					)
				)
				(= noIconHelp TRUE)
			)
			(verbTalk
				(if (theObj facingMe: ego)
					(Format @str 160 52 objDesc)
					(TimePrint @str)
				)
			)
			(verbDo
				(Format @str 160 53 objDesc)
				(TimePrint @str)
				(= noDoHelp TRUE)
			)
			(verbUse
				(= invDesc ((theIconBar curInvIcon?) description?))
				(Format @str 160 54 invDesc objDesc)
				(TimePrint @str)
			)
			(verbZipper
				(Format @str 160 55 objDesc)
				(TimePrint @str)
			)
			(else 
				(WriteDialog theObj theVerb)
			)
		)
	)
)
