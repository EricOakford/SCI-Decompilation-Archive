;;; Sierra Script 1.0 - (do not remove this comment)
(script# 870)
(include game.sh)
(use Main)
(use LLRoom)
(use RandCyc)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm870 0
)

(instance rm870 of LLRoom
	(properties
		lookStr {The studio floor of the K-RAP building is far underground, filled with soundproof studios and playback speakers. A large fountain, complete with giant bronze sculptures, fills the center of the room.}
		picture 870
		east 880
	)
	
	(method (init)
		(LoadMany VIEW 870 871 872 873)
		(LoadMany SOUND 853 800 801 802)
		(switch prevRoomNum
			(east
				(HandsOn)
				(= style 11)
				(ego init: actions: ActionsKRAP edgeHit: WEST normalize: 872)
				(elevator init: setPri: -1 addToPic:)
				(eDoor init: setPri: -1 addToPic:)
				(elevatorSide init: setPri: -1 addToPic:)
				(addToPics doit:)
				(door init: approachVerbs: verbDo)
				(keypad init: approachVerbs: verbDo verbLook)
				(studioARoom init:)
				(studioBRoom init:)
				(studioCRoom init:)
				(elevatorKeypad init: approachVerbs: verbDo verbLook)
			)
			(else 
				(HandsOn)
				(SetFFRoom 0)
				(ego init: normalize: 873 hide:)
				(door init:)
				(keypad init:)
				(studioARoom init:)
				(studioBRoom init:)
				(studioCRoom init:)
				(elevatorKeypad init:)
				(self setScript: goingDownScript)
			)
		)
		(statue init:)
		(dude1 init: setCycle: RandCycle)
		(dude2 init: setCycle: RandCycle)
		(dude3 init: setCycle: RandCycle)
		(super init:)
		(clothesRack init:)
		(addToPics doit:)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 100
						293 100
						261 63
						201 63
						173 100
						124 101
						104 134
						92 133
						76 134
						64 134
						54 146
						90 146
						73 168
						44 179
						24 174
						11 187
						135 187
						151 176
						186 162
						235 153
						319 153
						319 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						197 116
						204 110
						228 110
						233 115
						225 122
						204 122
					yourself:
				)
		)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(ActionsKRAP dispose:)
		(super dispose:)
	)
	
	(method (notify)
		(TimePrint 870 0)
		(HandsOn)
	)
)

(instance clothesRack of PicView
	(properties
		x 68
		y 158
		description {the clothes rack}
		lookStr {What a break! A clothing rack stands right beside your elevator.}
		view 870
		cel 3
		signal ignrAct
	)
)

(instance keypad of Feature
	(properties
		x 231
		y 35
		nsTop 29
		nsLeft 227
		nsBottom 41
		nsRight 235
		description {the keypad}
		sightAngle 40
		lookStr {This keypad is similar to those upstairs. Unfortunately, you don't know the combination to this one.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (!= (ego view?) 873)
					((ScriptID DIALER 0) init: 0 rm870)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance studioARoom of Feature
	(properties
		x 180
		y 46
		nsTop 28
		nsLeft 171
		nsBottom 64
		nsRight 190
		description {Studio A}
		sightAngle 40
	)
	
	(method (doVerb)
		(dude1 doVerb: &rest)
	)
)

(instance door of Feature
	(properties
		x 245
		y 42
		nsTop 24
		nsLeft 235
		nsBottom 60
		nsRight 255
		description {the studio door}
		sightAngle 40
		lookStr {This door leads to K-RAP's Studio B.}
	)
)

(instance studioBRoom of Feature
	(properties
		x 203
		y 37
		nsTop 24
		nsLeft 198
		nsBottom 51
		nsRight 208
		description {Studio B}
		sightAngle 40
		lookStr {This is K-RAP's Studio B.}
	)
)

(instance studioCRoom of Feature
	(properties
		x 278
		y 42
		nsTop 23
		nsLeft 268
		nsBottom 62
		nsRight 288
		description {Studio C}
		sightAngle 40
		lookStr {This is K-RAP's Studio C.}
	)
)

(instance statue of Feature
	(properties
		x 217
		y 1
		nsTop 20
		nsLeft 116
		nsBottom 189
		nsRight 319
		description {the statue}
		sightAngle 40
		onMeCheck cYELLOW
		lookStr {How impressive! K-RAP has a fountain filled with a huge statue of the King of Rock 'n' Roll--Barry Manilow!}
	)
)

(instance elevatorKeypad of Feature
	(properties
		x 9
		y 157
		nsTop 138
		nsLeft 4
		nsBottom 157
		nsRight 15
		description {the elevator keypad}
		sightAngle 40
		approachX 20
		approachY 180
		lookStr {This keypad is similar to those upstairs. Unfortunately, you don't know the combination to this one.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 870 1)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance clothes of View
	(properties
		x 68
		y 179
		z 20
		description {the clothes}
		sightAngle 40
		approachX 56
		approachY 162
		lookStr {A set of brightly-colored, over-sized rapper's clothes hangs on the clothes rack, conveniently placed near the elevator.}
		view 870
		cel 2
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (== (ego view?) 873)
					(ego setScript: getClothesScript)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance dude1 of Prop
	(properties
		x 142
		y 71
		description {that "2 Live 2 Screw" dude}
		view 870
		loop 2
		cycleSpeed 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(TimePrint 870 2)
			)
			(verbDo
				(TimePrint 870 3)
			)
			(verbZipper
				(TimePrint 870 4)
			)
			(verbUse
				(TimePrint 870 5)
			)
			(else
				(TimePrint 870 6)
			)
		)
	)
)

(instance dude2 of Prop
	(properties
		x 134
		y 68
		description {that "2 Live 2 Screw" dude}
		view 870
		loop 3
		cycleSpeed 10
	)
	
	(method (doVerb theVerb theItem)
		(dude1 doVerb: theVerb theItem)
	)
)

(instance dude3 of Prop
	(properties
		x 150
		y 63
		description {that "2 Live 2 Screw" dude}
		view 870
		loop 4
		cycleSpeed 8
	)
	
	(method (doVerb theVerb theItem)
		(dude1 doVerb: theVerb theItem)
	)
)

(instance elevator of View
	(properties
		x 44
		y 171
		description {the showervator}
		view 870
		priority 8
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(TimePrint 870 7)
			)
			(verbDo
				(TimePrint 870 8)
			)
			(verbZipper
				(TimePrint 870 9)
			)
			(verbUse
				(TimePrint 870 10)
			)
			(else
				(TimePrint 870 11)
			)
		)
	)
)

(instance eDoor of Prop
	(properties
		x 44
		y 171
		description {the showervator}
		view 870
		loop 1
		priority 15
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(elevator doVerb: theVerb theItem)
	)
)

(instance elevatorSide of View
	(properties
		x 44
		y 171
		description {the showervator}
		view 870
		cel 1
		priority 15
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(elevator doVerb: theVerb theItem)
	)
)

(instance elevatorWithPatti of Actor
	(properties
		x 44
		y 5
		description {your naked body}
		lookStr {Down you come!}
		view 870
		loop 5
		signal ignrAct
	)
)

(instance goingDownScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theMusic setVol: lastVolume)
				(clothes init: setPri: 12)
				(elevatorWithPatti
					init:
					setLoop: 5
					setCel: 0
					moveSpeed: 3
					setMotion: MoveTo 44 171 self
				)
			)
			(1
				(theMusic2 release:)
				(= ticks 30)
			)
			(2
				(elevatorWithPatti dispose:)
				(elevator init: setPri: 12 stopUpd:)
				(eDoor init: setPri: -1 stopUpd:)
				(elevatorSide init: setPri: -1 stopUpd:)
				(ego
					show:
					x: 37
					y: 183
					z: 20
					setLoop: 4
					setCel: 0
					setPri: 12
					actions: ActionsKRAP
				)
				(HandsOn)
				(theIconBar disable: ICON_WALK)
				(self dispose:)
			)
		)
	)
)

(instance getClothesScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(eDoor startUpd: setCel: 0)
				(= seconds 3)
			)
			(1
				(TimePrint 870 12)
				(= seconds 3)
			)
			(2 (eDoor setCycle: EndLoop self))
			(3
				(ego
					x: 43
					y: 166
					z: 0
					setLoop: -1
					setCel: -1
					setMotion: MoveTo 56 162 self
				)
			)
			(4
				(SolvePuzzle 1 fWearingRapClothes)
				(clothes hide:)
				(ego
					view: 871
					setLoop: 0
					setCel: 0
					x: 69
					y: 160
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(5
				(ego
					normalize: 872
					x: 56
					y: 162
					loop: 0
					setMotion: MoveTo 78 166 self
				)
			)
			(6
				(TimePrint 870 13)
				(TimePrint 870 14)
				(eDoor setCycle: BegLoop self)
			)
			(7
				(eDoor setPri: -1 stopUpd:)
				(theIconBar enable: ICON_WALK)
				(door approachVerbs: verbDo)
				(keypad approachVerbs: verbDo verbLook)
				(elevatorKeypad approachVerbs: verbDo verbLook)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance ActionsKRAP of Actions
	
	(method (doit)
		(return FALSE)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(verbDo
					(if (== (ego view?) 873)
						(TimePrint 870 15)
						(return TRUE)
					else
						(TimePrint 870 16)
						(return TRUE)
					)
				)
				(verbZipper
					(if (== (ego view?) 873)
						(TimePrint 870 15)
						(return TRUE)
					else
						(TimePrint 870 17)
						(return TRUE)
					)
				)
				(else
					(return FALSE)
				)
			)
		)
	)
)
