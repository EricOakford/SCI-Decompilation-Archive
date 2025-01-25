;;; Sierra Script 1.0 - (do not remove this comment)
(script# 320)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use EgoDead)
(use OccCyc)
(use Array)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use StopWalk)
(use Grooper)
(use Sight)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm320 0
)

(local
	[local0 2]
	eventNum
	theGGX
	local4
	local5
	local6
	local7
	local8
	local9
	local10
	local11
	local12
	local13
)
(instance rm320 of GloryRm
	(properties
		noun 48
		picture 320
	)
	
	(method (init)
		(= eventNum
			(cond 
				((not (Btst 126)) 1)
				(
					(and
						(Btst 37)
						(OneOf timeODay 4 5)
						(not (Btst 39))
						(not (Btst 48))
						(not (Btst 131))
					)
					6
				)
				(
					(and
						(Btst 48)
						(Btst 39)
						(not (Btst 132))
						(OneOf timeODay 4 5)
					)
					7
				)
				(
					(and
						(>= timeODay 6)
						(Btst 128)
						(not (Btst 135))
						(>= Day (+ gCurrentDay_7 1))
						(not (Btst 138))
					)
					13
				)
				(
					(and
						(>= timeODay 6)
						(Btst 136)
						(not (Btst 137))
						(== prevRoomNum 330)
					)
					14
				)
				(
					(and
						(>= timeODay 6)
						(Btst 128)
						(Btst 138)
						(not (Btst 163))
					)
					15
				)
				((and (not (Btst 127)) (OneOf timeODay 4 5)) 2)
				(
					(and
						(>= timeODay 6)
						(not (Btst 128))
						(not (Btst 136))
					)
					3
				)
				(
					(and
						(Btst 127)
						(>= Day 3)
						(OneOf timeODay 4 5)
						(not (Btst 129))
						(not (Btst 130))
					)
					4
				)
				(
					(and
						(OneOf timeODay 4 5)
						(Btst 130)
						(not (Btst 146))
						(!= prevRoomNum 240)
					)
					5
				)
				(
					(and
						(<= timeODay 3)
						(== heroType 3)
						(Btst 132)
						(!= piotyrVisits 0)
						(not (Btst 133))
					)
					8
				)
				(
				(and (<= timeODay 3) (Btst 115) (not (Btst 134))) 9)
				(
					(and
						(not (Btst 132))
						(or (<= timeODay 3) (OneOf timeODay 4 5))
					)
					10
				)
				((and (Btst 132) (not (Btst 134))) 11)
				((Btst 134) 12)
				(else 0)
			)
		)
		(theMusic number: 320 setLoop: -1 play:)
		(walkHandler addToFront: self)
		(= local8 (IntArray with: 2 6 0 4 1 7 3 5))
		(= local9 (IntArray with: 2 4 0 6 3 7 1 5))
		(ego init: normalize:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						6
						173
						0
						189
						0
						0
						319
						0
						319
						189
						245
						189
						245
						187
						294
						187
						340
						119
						276
						49
						252
						48
						165
						85
						192
						85
						267
						52
						324
						119
						287
						184
						257
						184
						257
						175
						183
						175
						183
						181
						120
						181
						120
						175
						143
						175
						143
						171
						105
						171
						105
						173
						50
						173
						50
						159
						80
						159
						80
						158
						49
						158
						49
						173
					yourself:
				)
		)
		(if (OneOf prevRoomNum 260 4)
			(ego posn: 193 270 setScaler: Scaler 130 79 189 160)
		else
			(ego setScaler: Scaler 63 77 88 51 looper: myLooper)
			(switch prevRoomNum
				(330
					(ego setPri: 40 posn: 273 49)
				)
				(240
					(ego setPri: 0 posn: 193 87)
				)
				(else  (ego posn: 106 184))
			)
		)
		(innkeepDoor init: approachVerbs: 4)
		(railing init: approachVerbs: 4)
		((ScriptID 325 0) init: approachVerbs: 4)
		((ScriptID 325 1) init: approachVerbs: 4)
		((ScriptID 325 2) init: approachVerbs: 4)
		((ScriptID 325 3) init: approachVerbs: 4)
		((ScriptID 325 4) init: approachVerbs: 4)
		(chair4 init:)
		((ScriptID 325 5) init: approachVerbs: 4)
		((ScriptID 325 6) init: approachVerbs: 4)
		((ScriptID 325 7) init: approachVerbs: 4)
		((ScriptID 325 8) init: approachVerbs: 4)
		((ScriptID 325 9) init: approachVerbs: 4)
		((ScriptID 325 10) init: approachVerbs: 4)
		((ScriptID 325 11) init: approachVerbs: 4)
		((ScriptID 325 12) init: approachVerbs: 4)
		(cabinet init: approachVerbs: 4 1)
		((ScriptID 325 13) init: approachVerbs: 4)
		(bedroomDoor init: approachVerbs: 4)
		((ScriptID 325 14) init: approachVerbs: 4)
		(gnomeDoor init: approachVerbs: 4 28 42)
		((ScriptID 325 15) init: approachVerbs: 4)
		((ScriptID 325 16) init: approachVerbs: 4)
		((ScriptID 325 17) init: approachVerbs: 4)
		((ScriptID 325 18) init: approachVerbs: 4)
		((ScriptID 325 19) init: approachVerbs: 4)
		(fire init: setCycle: Fwd)
		(fireplaceSlab init: setCycle: Fwd)
		(woodFloor init: setCycle: Fwd)
		(jug init: setCycle: Fwd)
		(if
			(or
				(== eventNum 1)
				(and
					(OneOf eventNum 2 4 5 6 7 10 11 12)
					(OneOf timeODay 4 5)
				)
			)
			(peasant1
				init:
				approachVerbs: 4 2
				setScript: sPeasantsTalk
			)
			(peasant2 init: approachVerbs: 4 2)
			(peasant3 init: setPri: 170 approachVerbs: 4 2)
		)
		(if
			(or
				(== eventNum 1)
				(and
					(OneOf eventNum 2 4 5 6 7 8 9 10 11 12)
					(or (OneOf timeODay 4 5) (<= timeODay 3))
				)
			)
			(innKeeper
				init:
				setPri: 170
				approachVerbs: 4
				setScript: sInnKeeperSmoke
			)
		)
		(if
			(or
				(== eventNum 1)
				(and
					(OneOf eventNum 2 6 7 8 9 10 11 12)
					(or (OneOf timeODay 4 5) (<= timeODay 3))
				)
			)
			(bella init: approachVerbs: 4)
		)
		(if (OneOf eventNum 4 5)
			(gnome init: approachVerbs: 4 setPri: 180)
		)
		(if (== eventNum 9) (child init: approachVerbs: 4))
		(if (OneOf eventNum 3 13 15)
			(domovoi init: approachVerbs: 4 2)
		)
		(super init: &rest)
		(PalVary pvINIT 320 0)
		(switch prevRoomNum
			(240
				(self setScript: sExitGnome)
			)
			(260
				(self setScript: sEnterFromTown)
			)
			(4
				(self setScript: sEnterFromTown)
			)
			(330
				(self setScript: sOutBedroomDoor)
			)
			(else  (theGame handsOn:))
		)
	)
	
	(method (doit)
		(if local5 (Palette palSET_FLAG 66 85 100))
		(if
			(and
				(Btst 72)
				(ego mover?)
				(User canControl:)
				(User canInput:)
			)
			(ego setPri: 170 x: 206 y: 168 setMotion: 0)
			(= local6
				(approachCode doit: ((user curEvent?) message?))
			)
			(= theGGX mouseX)
			(= local4 (- mouseY 10))
			(curRoom setScript: sHeroSit)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(PalVary pvUNINIT)
		(DisposeScript 321)
		(DisposeScript 322)
		(DisposeScript 323)
		(DisposeScript 324)
		(DisposeScript 325)
		(walkHandler delete: self)
		(local8 dispose:)
		(local9 dispose:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if
					(and
						(<= mouseX 248)
						(>= (- mouseY 10) 188)
						(not (Btst 72))
					)
					(ego setScript: sExitInn)
				else
					(ego setMotion: PolyPath mouseX (- mouseY 10))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sEnterFromTown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(if (Btst 70)
					(Bclr 70)
					(messager say: 22 6 104 0 self)
				else
					(= cycles 1)
				)
			)
			(2
				(ego setMotion: MoveTo 193 184 self)
			)
			(3
				(self setScript: sInitShit self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sInitShit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(if
					(and
						(= local7
							(/ (+ (* (+ (- Day gCurrentDay_6) 1) 15) 99) 100)
						)
						(> local7 global468)
						(cast contains: innKeeper)
						(or
							(>= ((inventory at: 0) amount?) 1)
							(>= kopeks 100)
						)
					)
					(= local12 1)
				)
				(if
					(and
						(< (ego y?) 105)
						(or local12 (OneOf eventNum 1 4 5))
					)
					(= state 3)
					(= local11 1)
					(ego setMotion: PolyPath 268 51 self)
				else
					(= local11 0)
					(= cycles 1)
				)
			)
			(1
				1
				(if
				(OneOf eventNum 1 2 3 4 5 6 7 8 9 10 11 12 13 15 16)
					((ScriptID 324 0) init: eventNum ego 324)
				)
				(switch eventNum
					(1
						(Bset 126)
						(if Night
							(Bset 127)
							(messager say: 22 6 23)
						else
							(messager say: 22 6 20)
						)
					)
					(2
						(Bset 127)
						(messager say: 22 6 26)
					)
					(3 (messager say: 22 6 3))
					(4
						(self setScript: sGnomeEntertain self 1)
					)
					(5
						(Bset 162)
						(messager say: 25 6 46)
					)
					(6
						(Bset 131)
						(messager say: 26 6 47)
					)
					(7
						(Bset 132)
						(messager say: 26 6 48)
					)
					(8 (= cycles 1))
					(9
						(Bset 134)
						(messager say: 12 6 13)
					)
					(13
						(Bset 135)
						(messager say: 22 6 28)
					)
					(14 (messager say: 22 6 30))
					(15
						(Bset 139)
						(messager say: 22 6 29)
					)
				)
				(if (not (== eventNum 4)) (= cycles 1))
			)
			(2
				2
				(if (== eventNum 14)
					(messager say: 22 6 108 0 self)
				else
					(= cycles 1)
				)
			)
			(3
				3
				(if local12
					(= local12 0)
					(self setScript: sPayKeeper self)
				else
					(= cycles 1)
				)
			)
			(4
				4
				(if (not local13) (theGame handsOn:))
				(self dispose:)
			)
		)
	)
)

(instance sUpStairs of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(not (ego mover?))
				(ego cycler?)
				((ego cycler?) isKindOf: StopWalk)
			)
			(= register 1)
			(self changeState: state)
		)
		(cond 
			((and (== state 0) (<= (ego y?) 180)) (self cue:))
			((and (== state 1) (<= (ego y?) 128)) (self cue:))
			((and (== state 2) (<= (ego y?) 53)) (self cue:))
			((and (== state 3) (<= (ego x?) 263)) (self cue:))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setPri: 196 setScaler: Scaler 130 77 189 51)
				(if register (ego setMotion: MoveTo 298 186 self))
			)
			(1
				(if register (ego setMotion: MoveTo 341 128 self))
			)
			(2
				(ego setPri: 64)
				(if register (ego setMotion: MoveTo 273 51 self))
			)
			(3
				(ego setPri: -1 setScaler: Scaler 63 77 88 51)
				(if register (ego setMotion: PolyPath 263 52 self))
			)
			(4
				(ego looper: myLooper)
				(theGame handsOn:)
				(= register 0)
				(self dispose:)
			)
		)
	)
)

(instance sDownStairs of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(not (ego mover?))
				(ego cycler?)
				((ego cycler?) isKindOf: StopWalk)
			)
			(= register 1)
			(self changeState: state)
		)
		(cond 
			((and (== state 0) (>= (ego y?) 128)) (self cue:))
			((and (== state 1) (>= (ego y?) 180)) (self cue:))
			((and (== state 2) (<= (ego x?) 263)) (self cue:))
			((and (== state 3) (<= (ego x?) 257)) (self cue:))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setLoop: -1
					setLooper: (ScriptID 28 1) 1
					setCycle: StopWalk 5
					setHeading: 135
					setPri: 64
					setScaler: Scaler 130 77 189 51
				)
				(if register (ego setMotion: MoveTo 341 128 self))
			)
			(1
				(ego setPri: 196)
				(if register (ego setMotion: MoveTo 298 186 self))
			)
			(2
				(ego setScaler: Scaler 130 79 189 160)
				(if register (ego setMotion: MoveTo 263 186 self))
			)
			(3
				(ego setPri: -1)
				(if register (ego setMotion: PolyPath 257 186 self))
			)
			(4
				(if local11 (= next sInitShit))
				(theGame handsOn:)
				(= register 0)
				(self dispose:)
			)
		)
	)
)

(instance sInBedroomDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 3 4 0 0 self)
			)
			(1
				(theMusic2 number: 960 loop: 1 play:)
				(bedroomDoor setCycle: End self)
			)
			(2
				(if (== eventNum 5)
					(self setScript: sGnomeLeaves self)
				else
					(= cycles 1)
				)
			)
			(3
				(ego setPri: 40 setMotion: MoveTo 273 49 self)
			)
			(4
				(bedroomDoor setCycle: Beg self)
			)
			(5
				(theMusic2 number: 961 loop: 1 play:)
				(= seconds 2)
			)
			(6
				(Bclr 162)
				(theGame handsOn:)
				(curRoom newRoom: 330)
			)
		)
	)
)

(instance sOutBedroomDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic2 number: 960 loop: 1 play:)
				(bedroomDoor setCycle: End self)
			)
			(1
				(ego setMotion: MoveTo 244 55 self)
			)
			(2
				(bedroomDoor setCycle: Beg self)
			)
			(3
				(theMusic2 number: 961 loop: 1 play:)
				(= local13 1)
				(ego setPri: -1)
				(self setScript: sInitShit self)
			)
			(4
				(= local13 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sInnKeeperSmoke of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(innKeeper
					setCycle: OccCyc self 1 5 360 (Random 1 2) self
				)
			)
			(1 (= state -1) (= cycles 1))
		)
	)
)

(instance sPeasantsTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(= state
					(switch (Random 0 4)
						(0 0)
						(1 1)
						(2 3)
						(3 4)
						(4 5)
					)
				)
				(= seconds (Random 2 6))
			)
			(1
				1
				(= state 6)
				(peasant1 loop: 0 cel: 0 setCycle: End self)
			)
			(2
				2
				(peasant1 loop: 0 cel: 0 setCycle: CT 3 1 self)
			)
			(3
				3
				(= state 6)
				(peasant1 setCycle: Beg self)
			)
			(4
				4
				(= state 6)
				(peasant1 loop: 0 cel: 10)
				(= seconds 2)
			)
			(5
				5
				(= state 6)
				(peasant1 loop: 1 cycleSpeed: 12 setCycle: End self)
			)
			(6
				6
				(peasant2 loop: 2 cel: 0 setCycle: End self)
			)
			(7 7 (= cycles 6))
			(8
				8
				(peasant1 loop: 0 cel: 0 cycleSpeed: 6)
				(peasant2 loop: 2 cel: 0)
				(= state -1)
				(= cycles 1)
			)
		)
	)
)

(instance sGnomeEntertain of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(Bset 129)
				(if register
					(gnome setCycle: End self)
				else
					(self changeState: 3)
				)
			)
			(1
				1
				(gnome loop: 4 setCycle: End self)
			)
			(2
				2
				(= state 5)
				(Bset 162)
				(messager say: 25 6 45 0 self)
			)
			(3
				3
				(Bclr 162)
				(if (== eventNum 4)
					(messager say: 25 6 38 0 self)
				else
					(messager say: 25 6 40 0 self)
				)
			)
			(4
				(if (== eventNum 4)
					(messager say: 25 6 36 0 self)
				else
					(messager say: 25 6 37 0 self)
				)
			)
			(5
				(= state 5)
				(if (== eventNum 4)
					(messager say: 25 6 35 0 self)
				else
					(messager say: 25 6 41 0 self)
				)
			)
			(6
				6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sHeroSit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(if (Btst 72)
					(if
						(or
							(== local6 4)
							(& local6 (| $000a ((CueObj client?) _approachVerbs?)))
						)
						(ego setMotion: 0)
						(= cycles 1)
					else
						(self changeState: 19)
					)
				else
					(self changeState: 6)
				)
			)
			(1
				1
				(Bclr 72)
				(ego setCycle: Beg self)
			)
			(2
				2
				(ego
					normalize:
					setPri: 168
					setMotion: MoveTo 138 165 self
				)
			)
			(3
				3
				(if (cast contains: gnome) (gnome setCycle: Beg self))
				(ego setMotion: MoveTo 131 171 self)
			)
			(4
				4
				(if (cast contains: gnome) 0 else (= cycles 1))
			)
			(5
				5
				(ego setPri: -1)
				(if
				(& local6 (| $000a ((CueObj client?) _approachVerbs?)))
					(ego
						setMotion:
							PolyPath
							((CueObj client?) approachX?)
							(+ (ego z?) ((CueObj client?) approachY?))
							CueObj
					)
				else
					(ego setMotion: PolyPath theGGX local4)
				)
				(theGame handsOn:)
				(self dispose:)
			)
			(6
				6
				(ego setPri: 168 setMotion: MoveTo 138 165 self)
			)
			(7
				7
				(if (cast contains: gnome)
					(gnome view: 322 setLoop: 4 1 cel: 0 setCycle: End self)
				else
					(= cycles 1)
				)
			)
			(8
				8
				(ego setMotion: MoveTo 206 168 self)
			)
			(9
				9
				(Bset 72)
				(if (cast contains: tray) (= state 18))
				(ego view: 54 setLoop: 4 1 cel: 0 setCycle: End self)
			)
			(10
				10
				(if (Btst 162)
					(self setScript: sGnomeEntertain self 0)
				else
					(= cycles 1)
				)
			)
			(11
				11
				(if
					(and
						(cast contains: bella)
						(or
							(OneOf eventNum 6 7 8 9 11 12)
							(and (OneOf eventNum 1 2 10) (< freeMeals 2))
						)
					)
					(bella setCycle: Walk setMotion: MoveTo 193 159 self)
				else
					(self changeState: 19)
				)
			)
			(12
				12
				(bella setMotion: MoveTo 187 165 self)
			)
			(13
				13
				(cond 
					((Btst 3) (Bclr 3))
					((Btst 2) (Bclr 2))
					(else (++ freeMeals))
				)
				(bella
					view: 329
					loop: 0
					cel: 0
					posn: 185 161
					setCycle: End self
				)
			)
			(14
				14
				(if (>= timeODay 3)
					(messager say: 22 6 21 (Random 1 5) self)
				else
					(messager say: 22 6 19 (Random 1 5) self)
				)
			)
			(15
				15
				(tray init: setPri: 175)
				(if (OneOf eventNum 1 2 6 10)
					(bella loop: 1 cel: 0 setCycle: End self)
				else
					(= cycles 2)
				)
			)
			(16
				16
				(if (OneOf eventNum 7 8 9 11 12)
					(bella setScript: sBellaIncidental)
					(self changeState: 19)
				else
					(bella
						view: 327
						setLoop: 2 1
						cel: 0
						posn: 187 165
						setCycle: Walk
						setMotion: MoveTo 193 159 self
					)
				)
			)
			(17
				17
				(bella setMotion: MoveTo 239 159 self)
			)
			(18
				18
				(bella dispose:)
				(= cycles 1)
			)
			(19
				19
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sBellaIncidental of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch eventNum
					(7
						(messager say: 23 6 32 0 self)
					)
					(8
						(Bset 133)
						(messager say: 23 6 31 0 self)
					)
					(9
						(messager say: 23 6 33 0 self)
					)
					(else  (= cycles 1))
				)
			)
			(1
				(if (== eventNum 8) ((ego actions?) dispose:))
				(= cycles 1)
			)
			(2
				(if (== eventNum 8) ((ScriptID 324 0) init: 16 ego 324))
				(= cycles 1)
			)
			(3
				(bella loop: 5 cel: 0 cycleSpeed: 8 setCycle: End self)
			)
			(4
				(bella loop: 6 cel: 0 setCycle: Fwd)
				(= seconds 300)
			)
			(5
				(bella loop: 3 cel: 0 setCycle: End self)
			)
			(6
				(bella loop: 4 cel: 0 setCycle: End self)
			)
			(7 (= seconds 4))
			(8 (bella setCycle: Beg self))
			(9
				(bella loop: 3 cel: 7 setCycle: Beg self)
			)
			(10 (self changeState: 1))
		)
	)
)

(instance sHeroShock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(ego view: 31 loop: 1 cel: 0 setCycle: CT 2 1 self)
			)
			(1
				1
				(theMusic2 number: 371 play:)
				(ego view: 31 loop: 6 cel: 0 setCycle: Fwd)
				(= seconds 3)
			)
			(2
				2
				(ego view: 31 loop: 1 cel: 2 setCycle: Beg self)
			)
			(3
				3
				(if (ego takeDamage: 10)
					(if register
						(= state 9)
						(= register 0)
						(messager say: 22 6 24 0 self)
					else
						(messager say: 22 6 25 0 self)
					)
				else
					(EgoDead 18 320 961 1 912)
				)
			)
			(4
				4
				(theMusic2 number: 960 loop: 1 play:)
				(gnomeDoor setPri: 0 setCycle: End self)
			)
			(5
				5
				(ego normalize: 7 setMotion: MoveTo 178 87 self)
			)
			(6
				6
				(ego setPri: 0 setMotion: MoveTo 193 87 self)
			)
			(7
				7
				(gnomeDoor setPri: 170 setCycle: Beg self)
			)
			(8
				8
				(theMusic2 number: 961 loop: 1 play:)
				(= seconds 2)
			)
			(9
				9
				(theGame handsOn:)
				(ego normalize:)
				(curRoom newRoom: 240)
			)
			(10
				10
				(theGame handsOn:)
				(ego normalize:)
				(self dispose:)
			)
		)
	)
)

(instance sGetDoll of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego get: 33)
				(Bset 163)
				(cabinetDoor init:)
				(doll init:)
				(= seconds 3)
			)
			(1 (doll hide:) (= seconds 2))
			(2 (messager say: 5 4 1 0 self))
			(3
				(cabinetDoor hide:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitGnome of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic2 number: 960 loop: 1 play:)
				(gnomeDoor setPri: 0 setCycle: End self)
			)
			(1
				(ego setMotion: MoveTo 178 87 self)
			)
			(2
				(ego setPri: -1 setMotion: MoveTo 200 76 self)
			)
			(3
				(gnomeDoor setCycle: Beg self)
			)
			(4
				(theMusic2 number: 961 loop: 1 play:)
				(self setScript: sInitShit self)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitInn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(if (< (ego y?) 105)
					(= state 6)
					(ego setMotion: PolyPath 268 51 self)
				else
					(= cycles 1)
				)
			)
			(1 1 (Face ego 193 189 self))
			(2
				2
				(cond 
					((and (<= 4 timeODay) (<= timeODay 5)) (self changeState: 3))
					((and (<= 6 timeODay) (<= timeODay 7))
						(if (cast contains: innKeeper)
							(self changeState: 3)
						else
							(messager say: 22 (= state 6) 16 0 self)
						)
					)
					(else (self changeState: 5))
				)
			)
			(3
				3
				(messager say: 22 6 16 0 self)
			)
			(4
				4
				(messager say: 12 6 100 0 self)
			)
			(5
				(if (== eventNum 5)
					(self setScript: sGnomeLeaves self)
				else
					(= cycles 1)
				)
			)
			(6
				6
				(Bclr 162)
				(theGame handsOff:)
				(curRoom south: 260)
				(ego setMotion: ((ScriptID 17) new:) 193 189 self)
			)
			(7
				7
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sPayKeeper of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (>= kopeks 100)
					(= kopeks (- kopeks 100))
				else
					((inventory at: 0)
						amount: (- ((inventory at: 0) amount?) local7)
					)
				)
				(= global468 (+ global468 local7))
				(= gCurrentDay_6 Day)
				(ego setMotion: PolyPath 186 183 self)
			)
			(1 (ego setHeading: 0 self))
			(2
				(messager say: 12 6 102 0 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGnomeLeaves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 146)
				(messager say: 25 6 39 0 self)
			)
			(1
				(messager say: 25 6 42 0 self)
			)
			(2 (self dispose:))
		)
	)
)

(instance gnomeDoorTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 4)
				(cond 
					((and (Btst 146) (not (cast contains: gnome))) (messager say: 9 4 8) (return 1))
					(
						(or
							(<= eventNum 3)
							(and (== eventNum 10) (not (Btst 129)))
							(cast contains: gnome)
						)
						(messager say: 9 4 103)
						(return 1)
					)
					(else (super doVerb: theVerb &rest))
				)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (sayMessage)
		(switch iconValue
			(9
				(self clean:)
				(curRoom setScript: sHeroShock)
			)
			(11
				(self clean:)
				(curRoom setScript: sHeroShock 0 1)
			)
			(12
				(self clean:)
				(curRoom setScript: sHeroShock 0 1)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super
			showCases: 12 (if (ego has: 13) else (ego has: 24))
		)
	)
)

(instance innKeepDoorTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(if (== iconValue 17) (Bset 137))
		(super sayMessage: &rest)
	)
	
	(method (showCases)
		(super
			showCases:
				12
				(if (ego has: 13) else (ego has: 24))
				10
				(if (<= eventNum 13) else (>= eventNum 15))
				17
				(if (== eventNum 14) (not (Btst 137)) else 0)
		)
	)
)

(instance bella of Actor
	(properties
		noun 23
		approachX 210
		approachY 175
		x 239
		y 159
		view 327
		loop 1
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(if (OneOf eventNum 7 8 9 11 12)
			((ScriptID 323 0) init: eventNum self 323)
		)
	)
	
	(method (handleEvent event)
		(if
		(and (== (event message?) JOY_UPRIGHT) (Btst 72))
			(self approachVerbs: 4)
		else
			(self approachVerbs: 4 2)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if (OneOf eventNum 1 2 10)
					(messager say: 22 6 27)
				else
					(super doVerb: theVerb)
				)
			)
			(59
				(ego use: 40)
				(super doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance gnome of Prop
	(properties
		noun 25
		approachX 156
		approachY 184
		x 151
		y 150
		view 320
		loop 3
		signal $4001
	)
	
	(method (init)
		(if (== eventNum 5) (= view 322) (= loop 4) (= cel 0))
		(super init: &rest)
		((ScriptID 322 0) init: eventNum self 322)
	)
	
	(method (handleEvent event)
		(if
		(and (== (event message?) JOY_UPRIGHT) (Btst 72))
			(self approachVerbs: 4)
		else
			(self approachVerbs: 4 2)
		)
		(super handleEvent: event &rest)
	)
)

(instance domovoi of Prop
	(properties
		noun 50
		approachX 66
		approachY 173
		x 30
		y 95
		fixPriority 1
		view 335
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		((ScriptID 324 1) init: eventNum self 324)
	)
)

(instance innKeeper of Prop
	(properties
		noun 12
		approachX 186
		approachY 183
		x 164
		y 167
		view 320
		loop 5
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		((ScriptID 321 0) init: eventNum self 321)
	)
	
	(method (handleEvent event)
		(if
		(and (== (event message?) JOY_UPRIGHT) (Btst 72))
			(self approachVerbs: 4)
		else
			(self approachVerbs: 4 2)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(15
				(if
					(and
						(= local7
							(/ (+ (* (+ (- Day gCurrentDay_6) 1) 15) 99) 100)
						)
						(> local7 global468)
					)
					(messager say: 12 15 105)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance child of Prop
	(properties
		noun 24
		approachX 210
		approachY 179
		x 226
		y 218
		z 50
		priority 170
		fixPriority 1
		view 655
		loop 1
		cel 6
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		((ScriptID 323 1) init: eventNum self 323)
	)
	
	(method (handleEvent event)
		(if
		(and (== (event message?) JOY_UPRIGHT) (Btst 72))
			(self approachVerbs: 4)
		else
			(self approachVerbs: 4 2)
		)
		(super handleEvent: event &rest)
	)
)

(instance peasant1 of Prop
	(properties
		noun 26
		approachX 116
		approachY 171
		x 125
		y 164
		priority 165
		fixPriority 1
		view 320
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		((ScriptID 322 1) init: eventNum self 322)
	)
)

(instance peasant2 of Prop
	(properties
		noun 26
		approachX 116
		approachY 171
		x 97
		y 164
		priority 170
		fixPriority 1
		view 320
		loop 2
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(= actions (ScriptID 322 1))
	)
)

(instance peasant3 of Prop
	(properties
		noun 26
		approachX 116
		approachY 171
		x 74
		y 181
		z 50
		view 320
		loop 6
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(= actions (ScriptID 322 1))
	)
)

(instance gnomeDoor of Prop
	(properties
		noun 9
		approachX 197
		approachY 84
		x 177
		y 44
		view 322
		loop 1
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(gnomeDoorTeller init: self 320 20 142)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(28
					(if (== (ego trySkill: 9 225) 1)
						(cond 
							((and (not (cast contains: gnome)) (Btst 146)) (messager say: 9 4 8) (return 1))
							(
								(or
									(<= eventNum 3)
									(and (== eventNum 10) (not (Btst 129)))
									(cast contains: gnome)
								)
								(messager say: 9 4 103)
								(return 1)
							)
							(else (curRoom setScript: sHeroShock 0 1))
						)
					else
						(messager say: 9 9 7)
					)
				)
				(42
					(if (== (ego trySkill: 9 200) 1)
						(cond 
							((and (not (cast contains: gnome)) (Btst 146)) (messager say: 9 4 8) (return 1))
							(
								(or
									(<= eventNum 3)
									(and (== eventNum 10) (not (Btst 129)))
									(cast contains: gnome)
								)
								(messager say: 9 4 103)
								(return 1)
							)
							(else (curRoom setScript: sHeroShock 0 1))
						)
					else
						(messager say: 9 9 7)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance bedroomDoor of Prop
	(properties
		noun 3
		approachX 239
		approachY 58
		x 255
		y 5
		priority 30
		fixPriority 1
		view 322
		loop 3
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 277 46 277 55 267 55 267 46
						yourself:
					)
					6
					0
					4
					sDownStairs
				yourself:
			)
		)
	)
	
	(method (dispose)
		(heading dispose:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sInBedroomDoor)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fire of Prop
	(properties
		noun 10
		x 16
		y 167
		fixPriority 1
		view 321
		cel 2
		signal $4001
		cycleSpeed 8
		detailLevel 3
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(> (theGame detailLevel:) 1)
				(< (GetDistance x y (ego x?) (ego y?) 0) 45)
			)
			(= local5 1)
			(switch (Random 0 2)
				(0
					(Palette palSET_FLAG 78 79 88)
					(Palette palSET_FLAG 84 85 88)
				)
				(1
					(Palette palSET_FLAG 78 79 62)
					(Palette palSET_FLAG 84 85 62)
				)
				(else 
					(Palette palSET_FLAG 78 79 75)
					(Palette palSET_FLAG 84 85 75)
				)
			)
		)
	)
)

(instance cabinetDoor of Prop
	(properties
		noun 5
		x 56
		y 114
		fixPriority 1
		view 322
		cel 1
		signal $4001
	)
)

(instance fireplaceSlab of Prop
	(properties
		x 16
		y 167
		priority 1
		fixPriority 1
		view 321
		loop 4
		signal $4001
		cycleSpeed 10
		detailLevel 3
	)
)

(instance woodFloor of Prop
	(properties
		x 84
		y 165
		fixPriority 1
		view 321
		loop 5
		cel 1
		signal $4001
		cycleSpeed 10
		detailLevel 3
	)
)

(instance jug of Prop
	(properties
		noun 1
		x 34
		y 145
		fixPriority 1
		view 321
		loop 1
		signal $4001
		detailLevel 3
	)
)

(instance doll of View
	(properties
		x 67
		y 119
		priority 1
		fixPriority 1
		view 321
		loop 6
		signal $4000
	)
)

(instance tray of View
	(properties
		noun 49
		x 180
		y 146
		view 329
		loop 2
		signal $4000
	)
)

(instance railing of Feature
	(properties
		noun 17
		sightAngle 180
		x 172
		y 72
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					init: 151 95 242 58 298 120 273 146 271 181 318 130 317 103 240 28 151 73
					yourself:
				)
		)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 286 182 286 189 259 189 268 182
						yourself:
					)
					6
					0
					4
					sUpStairs
				yourself:
			)
		)
	)
	
	(method (dispose)
		(heading dispose:)
		(super dispose: &rest)
	)
)

(instance chair4 of Feature
	(properties
		noun 6
		nsLeft 204
		nsTop 121
		nsRight 222
		nsBottom 148
		sightAngle 180
		x 213
		y 134
	)
	
	(method (handleEvent event)
		(switch (event message?)
			(JOY_UP
				(self approachVerbs: 4 1)
				(= approachX 212)
				(= approachY 179)
			)
			(JOY_DOWNRIGHT
				(if (Btst 72)
					(self approachVerbs: 0)
				else
					(self approachVerbs: 4 1)
					(= approachX 131)
					(= approachY 171)
				)
			)
		)
		(super handleEvent: event)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (Btst 72)
					(messager say: 22 6 107)
				else
					(ego setScript: sHeroSit)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cabinet of Feature
	(properties
		noun 5
		nsLeft 52
		nsTop 107
		nsRight 94
		nsBottom 157
		sightAngle 180
		approachX 80
		approachY 159
		x 73
		y 132
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (not (Btst 163))
					(messager say: 5 1 5)
				else
					(messager say: 5 1 4)
				)
			)
			(4
				(cond 
					(
					(and (>= timeODay 6) (Btst 139) (not (Btst 163))) (curRoom setScript: sGetDoll))
					((>= timeODay 6) (super doVerb: theVerb &rest))
					(else (messager say: 5 4 3))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance innkeepDoor of Feature
	(properties
		noun 13
		nsLeft 260
		nsTop 103
		nsRight 290
		nsBottom 175
		sightAngle 180
		approachX 255
		approachY 178
		x 275
		y 139
	)
	
	(method (init)
		(super init: &rest)
		(innKeepDoorTeller init: self 320 20 143)
	)
)

(instance myLooper of Grooper
	(properties)
	
	(method (doit theClient param2 theCaller param4 &tmp temp0 temp1)
		(if
		(and (ego cycler?) ((ego cycler?) isKindOf: StopWalk))
			(ego view: ((ego cycler?) vWalking?))
		)
		(if (not client) (= client theClient))
		(if (>= argc 3) (= caller theCaller))
		(if (& (client signal?) $0800)
			(if caller (caller cue:))
			(= caller 0)
			(return)
		)
		(= temp1 (if (< (NumLoops client) 8) 4 else 8))
		(if
			(or
				(not (cast contains: client))
				(and (>= argc 4) param4)
			)
			(client
				loop:
					(local9
						at:
							(*
								(if (== temp1 4) 2 else 1)
								(/
									(umod (+ (client heading?) (/ 180 temp1)) 360)
									(/ 360 temp1)
								)
							)
					)
			)
			(if caller (caller cue:))
			(= caller 0)
			(return)
		)
		(= temp0
			(if
				(and
					(== (client loop?) (- (NumLoops client) 1))
					((client cycler?) isKindOf: StopWalk)
					(== ((client cycler?) vStopped?) -1)
				)
				(local8 at: (client cel?))
			else
				(local8 at: (client loop?))
			)
		)
		(if oldMover (oldMover dispose:) (= oldMover 0))
		(if
			(and
				oldCycler
				(or
					(oldCycler isMemberOf: Grycler)
					(not ((client cycler?) isMemberOf: Grycler))
				)
			)
			(oldCycler dispose:)
			(= oldCycler 0)
		)
		(if (not oldCycler) (= oldCycler (client cycler?)))
		(if
			(and
				(client cycler?)
				((client cycler?) isMemberOf: Grycler)
			)
			((client cycler?) dispose:)
		)
		(= oldMover (client mover?))
		(client
			cycler: 0
			mover: 0
			setMotion: 0
			setCycle: myGradualCycler self temp0
		)
	)
	
	(method (cue &tmp theCaller)
		(cond 
			((not (client mover?)) (client mover: oldMover))
			(oldMover (oldMover dispose:))
		)
		(if oldCycler
			(if (client cycler?)
				(oldCycler dispose:)
			else
				(client cycler: oldCycler)
			)
		)
		(= theCaller caller)
		(= caller (= oldMover (= oldCycler 0)))
		(if theCaller (theCaller cue: &rest))
	)
)

(instance myGradualCycler of Grycler
	(properties)
	
	(method (init param1 theCaller theLoopIndex)
		(super init: param1)
		(= caller theCaller)
		(= numOfLoops (if (< (NumLoops client) 8) 4 else 8))
		(= cycleDir
			(-
				(sign
					(AngleDiff (* theLoopIndex 45) (param1 heading?))
				)
			)
		)
		(= loopIndex theLoopIndex)
		(if (self loopIsCorrect:)
			(if
				(and
					(((client looper?) oldCycler?) isKindOf: StopWalk)
					(== (((client looper?) oldCycler?) vStopped?) -1)
				)
				(client loop: (local9 at: loopIndex))
			)
			(self cycleDone:)
		)
	)
	
	(method (nextCel)
		(if
			(or
				(< (Abs (- gameTime cycleCnt)) (client cycleSpeed?))
				(self loopIsCorrect:)
			)
			(client loop?)
		else
			(= cycleCnt gameTime)
			(= loopIndex
				(+ loopIndex (* cycleDir (/ 8 numOfLoops)))
			)
			(= loopIndex (umod loopIndex 8))
			(local9 at: loopIndex)
		)
	)
)
