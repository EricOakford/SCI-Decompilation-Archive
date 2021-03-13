;;; Sierra Script 1.0 - (do not remove this comment)
(script# 300)
(include game.sh) (include "300.shm")
(use Main)
(use TellerIcon)
(use GloryTalker)
(use PAvoid)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use StopWalk)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm300 0
	survivorTalker 1
	welcomeTalker 2
)

(local
	toX
	toY
	theX
	theY
	egoPri
	eventX =  150
	eventY =  150
	local7
	theFeature
	local9
	msgOfTheDay =  1
	local11 = [
			0 -20 143 0 142 143 159 PATHEND
			143 159 0 142 -20 143 PATHEND
			-20 143 0 142 236 150 PATHEND
			236 150 0 142 -20 143 PATHEND
			]
	local40
	survivorWontTalk
	welcomeWomanHere
	survivorIsHere
	triedToPayInDinars
	flirted
	sitter1Cel = [0 1 2]
	sitter2Cel = [2 3 1]
	sitter3Cel = [3 2]
	sitter4Cel = [0 4 2]
	sitter5Cel = [2 5 6]
	sitter6Cel = [4 3 5]
	sitter7Cel = [5 6 4]
	sitter1Noun = [N_SITTER1 N_SITTER2 N_SITTER3]
	sitter2Noun = [N_SITTER3 N_SITTER4 N_SITTER5]
	sitter3Noun = [N_SITTER4 N_SITTER5 N_SITTER1]
	sitter4Noun = [N_SITTER6 N_SITTER7 N_SITTER8]
	sitter5Noun = [N_SITTER8 N_SITTER9 N_SITTER10]
	sitter6Noun = [N_SITTER7 N_SITTER11 N_SITTER9]
	sitter7Noun = [N_SITTER9 N_SITTER10 N_SITTER7]
	sitterIndex
	local89 = [0 -19 -4 44 -2 -38 33 -12 -3 -40 58 -34 37 -26 -5 999]
	local105 = [0 -20 999]
	local108 = [0 -21 25 24 999]
	local113 = [0 23 22 999]
	local117 = [0 13 -14 18 999]
	local122 = [0 15 16 17 999]
	local127 = [0 27 28 29 30 31 32 999]
	local135 = [0 -9 999]
	local138 = [0 -62 999]
	local141 = [0 -63 999]
	local144 = [0 39 999]
	local147 = [0 -41 999]
	local150 = [0 42 43 999]
	local154 = [0 35 36 999]
	local158 = [0 -70 1 -60 69 68 64 -6 999]
	local167 = [0 -17 -53 -32 33 999]
	local173 = [0 -46 -49 -52 999]
	local178 = [0 -47 -66 999]
	local182 = [0 -48 999]
	local185 = [0 -50 -51 999]
	local189 = [0 -65 999]
	local192 = [0 54 55 999]
	local196 = [0 -56 999]
	local199 = [0 67 999]
	[local202 4]
	[local206 19]
	local225 = [0 -19 -20 -21 -12 -14 -26 -2 -3 -5 -38 -40 -41 -34 999]
	[local240 15]
	local255 = [0 -17 -53 -32 -46 -49 -52 -47 -56 999]
)
(procedure (InnSet flagEnum)
	(|= innFlags flagEnum)
)

(procedure (InnClr flagEnum)
	(&= innFlags (~ flagEnum))
)

(procedure (InnTst flagEnum)
	(return (& innFlags flagEnum))
)

;inn flags
(define FIRST_TIME				$0001)
(define SECOND_TIME				$0002)
(define SURVIVOR_WILL_COME		$0004)
(define SURVIVOR_DEAD			$0008)
(define SURVIVOR_WAS_HERE		$0010)
(define SURVIVOR_DONE			$0020)
(define ASKED_ABOUT_RESTAURANT	$0040)
(define ORDERED_FOOD			$0080)
(define JANNA_WAITS				$0100)
(define TALKED_TO_SURVIVOR		$0200)

(procedure (DailyMsg n v c &tmp i)
	(for ((= i 1)) (Message MsgGet curRoomNum n v c i) ((++ i)))
	(return (-- i))
)

(instance rm300 of Room
	(properties
		noun N_ROOM
		picture 300
		vanishingY 30
	)
	
	(method (init)
		(if (== prevRoomNum 270)
			(cSound hold: 0 setVol: 127)
		)
		(ego
			noun: N_EGO_TELL
			init:
			normalize:
			setScale: Scaler 95 60 180 30
		)
		(= [local202 0] @local158)
		(= [local206 0] @local89)
		(= [local206 1] @local105)
		(= [local206 2] @local108)
		(= [local206 3] @local113)
		(= [local206 4] @local117)
		(= [local206 5] @local122)
		(= [local206 6] @local127)
		(= [local206 7] @local135)
		(= [local206 8] @local138)
		(= [local206 9] @local141)
		(= [local206 10] @local144)
		(= [local206 11] @local147)
		(= [local206 12] @local150)
		(= [local206 13] @local154)
		(= [local240 0] @local167)
		(= [local240 1] @local173)
		(= [local240 2] @local192)
		(= [local240 3] @local196)
		(= [local240 4] @local178)
		(= [local240 5] @local185)
		(= [local240 6] @local189)
		(= [local240 7] @local182)
		(= [local240 8] @local199)
		(egoTell init: ego @local158 @local202)
		(welcomeTell
			init: welcomeWoman @local89 @local206 @local225
		)
		(survivorTell
			init: survivor @local167 @local240 @local255
		)
		(walkHandler addToFront: curRoom)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						177 189
						177 187
						313 187
						313 172
						232 171
						221 162
						208 159
						192 151
						235 150
						256 160
						247 141
						273 141
						287 150
						312 146
						312 138
						297 138
						282 117
						274 117
						288 139
						275 139
						239 130
						238 137
						214 137
						197 115
						169 107
						157 106
						182 116
						199 139
						168 139
						140 127
						97 127
						101 140
						68 140
						63 115
						59 107
						39 107
						35 114
						25 141
						4 141
						4 146
						31 146
						34 152
						80 154
						80 177
						3 179
						3 187
						176 187
						176 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						87 160
						114 157
						122 166
						137 165
						123 148
						159 148
						167 152
						187 151
						205 160
						218 163
						229 172
						195 176
						160 181
						118 178
						87 175
					yourself:
				)
		)
		(leftDoor init:)
		(rightDoor init:)
		(super init:)
		(cond 
			((>= Clock 750)
				(if (!= prevRoomNum 305)
					(cond 
						((not innFlags)
							(InnSet FIRST_TIME)
							(InnSet JANNA_WAITS)
						)
						((InnTst FIRST_TIME)
							(cond 
								(
									(and
										(not (InnTst SURVIVOR_WAS_HERE))
										(>= Day (+ innDay 1))
										(InnTst ORDERED_FOOD)
										(>= timeODay TIME_SUNSET)
									)
									(InnClr FIRST_TIME)
									(InnSet SURVIVOR_WILL_COME)
								)
								((InnTst ORDERED_FOOD)
									(InnClr FIRST_TIME)
									(InnSet SECOND_TIME)
								)
							)
						)
						(
							(and
								(not (InnTst SURVIVOR_WAS_HERE))
								(InnTst SECOND_TIME)
								(>= Day (+ innDay 1))
								(InnTst ORDERED_FOOD)
								(>= timeODay TIME_SUNSET)
							)
							(InnClr SECOND_TIME)
							(InnSet SURVIVOR_WILL_COME)
						)
						((InnTst SURVIVOR_WILL_COME)
							(cond 
								(
									(and
										(not (InnSet SURVIVOR_DONE))
										(>= Day (+ innDay 3))
										(InnTst SURVIVOR_WAS_HERE)
									)
									(= innDay Day)
									(InnClr SURVIVOR_WILL_COME)
									(InnSet SURVIVOR_DEAD)
								)
								((or (InnTst SURVIVOR_WAS_HERE) (< timeODay TIME_SUNSET))
									(InnClr SURVIVOR_WILL_COME)
									(InnSet SECOND_TIME)
								)
							)
						)
						(
							(and
								(not (InnSet SURVIVOR_DONE))
								(InnTst SECOND_TIME)
								(>= Day (+ innDay 3))
								(InnTst SURVIVOR_WAS_HERE)
							)
							(= innDay Day)
							(InnClr SECOND_TIME)
							(InnSet SURVIVOR_DEAD)
						)
						((and (InnTst SURVIVOR_DEAD) (!= innDay Day))
							(InnClr SURVIVOR_DEAD)
							(InnSet SURVIVOR_DONE)
							(InnSet SECOND_TIME)
						)
					)
					(if (and (InnTst JANNA_WAITS) (InnTst FIRST_TIME))
						(InnClr JANNA_WAITS)
						(welcomeWoman
							x: 37
							y: 179
							view: 303
							loop: 1
							cel: 0
							init:
							stopUpd:
						)
						(cond 
							((== prevRoomNum 310)
								(ego x: 302 y: 33)
								(self setScript: firstWalkIn)
							)
							((< (ego x?) 35)
								(= toX 197)
								(= toY 135)
								(ego y: 114 x: 179)
								(self setScript: firstWalkIn)
							)
							(else
								(= toX 47)
								(= toY 135)
								(ego y: 114 x: 47)
								(self setScript: firstWalkIn)
							)
						)
					else
						(welcomeWoman
							setCycle: StopWalk -1
							setAvoider: PAvoider
							init:
							stopUpd:
						)
						(cond 
							((== prevRoomNum 310)
								(ego x: 302 y: 33)
								(self setScript: sEnter self)
							)
							((< (ego x?) 35)
								(= toX 197)
								(= toY 135)
								(ego y: 114 x: 179)
								(self setScript: walkIn)
							)
							(else
								(= toX 47)
								(= toY 135)
								(ego y: 114 x: 47)
								(self setScript: walkIn)
							)
						)
					)
				else
					(welcomeWoman
						setCycle: StopWalk -1
						setAvoider: PAvoider
						init:
						stopUpd:
					)
				)
				(flameRight setCycle: Forward init:)
				(flameCenter setCycle: Forward init:)
				(flameLeft setCycle: Forward init:)
				(= sitterIndex (mod (+ Day 3) 3))
				(northSitter1
					loop: (mod (+ Day 2) 2)
					cel: [sitter1Cel sitterIndex]
					noun: [sitter1Noun sitterIndex]
					addToPic:
				)
				;EO: This code was a bit messy
				(eastSitter2
					cel: [sitter5Cel sitterIndex]
					noun: [sitter5Noun sitterIndex]
					addToPic:
				)
				(eastSitter1
					cel: [sitter4Cel sitterIndex]
					noun: [sitter4Noun sitterIndex]
					addToPic:
				)
				(if (and (not (InnTst SURVIVOR_WILL_COME)) (mod Day 2))
					(northSitter2
						loop: (mod (+ Day 2) 2)
						cel: [sitter2Cel sitterIndex]
						noun: [sitter2Noun sitterIndex]
						addToPic:
					)
					(northCushion3 approachVerbs: V_DO init:)
					(otherTray x: 132 y: 200 z: 35 priority: 14 addToPic:)
				else
					(northCushion2 init: approachVerbs: V_DO)
					(northSitter3
						loop: (mod (+ Day 2) 2)
						cel: [sitter3Cel sitterIndex]
						noun: [sitter3Noun sitterIndex]
						addToPic:
					)
				)
				(otherTray x: 250 y: 200 z: 41 addToPic:)
				(eastSitter3
					noun: N_OLIVER
					addToPic:
				)
				(westSitter2
					cel: [sitter7Cel sitterIndex]
					noun: [sitter7Noun sitterIndex]
					addToPic:
				)
				(cond 
					((not (InnTst SURVIVOR_WILL_COME))
						(westSitter1
							cel: [sitter6Cel sitterIndex]
							noun: [sitter6Noun sitterIndex]
							addToPic:
						)
					)
					((InnTst SURVIVOR_WAS_HERE)
						(= local40 1)
						(= survivorWontTalk (InnTst TALKED_TO_SURVIVOR))
						(= welcomeWomanHere 1)
						(survivor
							view: 307
							loop: 2
							cel: (if (== survivorTalkCount 1023) 3 else 1)
							x: 104
							y: 171
							init:
						)
					)
					(else
						(westCushion init:)
					)
				)
				(tray1 addToPic:)
				(tray2 addToPic:)
				(tray3 addToPic:)
				(tray4 addToPic:)
				(tray5 addToPic:)
				(tray6 addToPic:)
			)
			((!= prevRoomNum 305)
				(cond 
					((== prevRoomNum 310) (ego x: 302 y: 33)
						(self setScript: sEnter self)
					)
					((< (ego x?) 35)
						(= toX 197)
						(= toY 135)
						(ego y: 114 x: 179)
						(self setScript: walkIn)
					)
					(else
						(= toX 47)
						(= toY 135)
						(ego y: 114 x: 47)
						(self setScript: walkIn)
					)
				)
			)
		)
		(chandelier init:)
		(rightchandelier init:)
		(lefttable init:)
		(midtable init:)
		(righttable init:)
		(stairs init:)
		(board init: approachVerbs: V_LOOK)
	)
	
	(method (doit)
		(super doit: &rest)
		(Palette PALCycle 76 81 6)
		(cond 
			(script 0)
			((== (ego onControl: origin) cBLUE)
				(self setScript: toRoom)
			)
			((== (ego onControl: cBLUE) cGREEN)
				(curRoom newRoom: 270)
			)
			((and (ego mover?) (== (ego view?) 40))
				(ego setMotion: 0 setScript: standNorth))
		)
	)
	
	(method (dispose)
		(UnLoad RES_VIEW 300)
		(UnLoad RES_VIEW 301)
		(UnLoad RES_VIEW 302)
		(UnLoad RES_VIEW 303)
		(UnLoad RES_VIEW 306)
		(walkHandler delete: curRoom)
		(if survivorWontTalk
			(InnSet TALKED_TO_SURVIVOR)
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(egoActions doVerb: V_WALK)
			)
			(else 
				(if (< Clock 750)
					(messager say: NULL V_DOIT C_MIDNIGHT)
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance firstWalkIn of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== prevRoomNum 310)
					(self setScript: sEnter self)
				else
					(HandsOff)
					(ego setMotion: PolyPath toX toY self)
				)
			)
			(1
				(HandsOff)
				(welcomeWoman setCycle: EndLoop self)
			)
			(2
				(welcomeWoman
					view: 301
					loop: 4
					cel: 5
					setCycle: StopWalk -1
					setAvoider: PAvoider
				)
				(= cycles 2)
			)
			(3
				(messager say: N_WELCOME_WOMAN V_DOIT C_FIRST_WALK_IN 0 self)
			)
			(4
				(welcomeWoman setMotion: MoveTo -20 174 self)
			)
			(5
				(welcomeWoman y: 145 stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance walkIn of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath toX toY self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sEnter of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 273 81 self)
			)
			(1
				(ego setMotion: MoveTo 250 79 self)
			)
			(2
				(ego setMotion: MoveTo 295 139 self)
			)
			(3
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance toRoom of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 250 79 self)
			)
			(1
				(ego setMotion: MoveTo 273 81 self)
			)
			(2
				(ego setMotion: MoveTo 309 31 self)
			)
			(3
				(curRoom newRoom: 310)
			)
		)
	)
)

(instance survivorWalkIn of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= innDay Day)
				(= local40 1)
				(InnSet SURVIVOR_WAS_HERE)
				(survivor
					init:
					setCycle: Walk
					setMotion: MoveTo 104 171 self
				)
			)
			(1
				(survivor loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(2
				(survivor loop: 2 cel: 1)
				(self dispose:)
			)
		)
	)
)

(instance sitNorth of Script

	(method (changeState newState &tmp [temp0 20])
		(switch (= state newState)
			(0
				(if (not local40)
					(= local9 1)
				)
				(CueObj client: 0)
				(HandsOff)
				(ego setPri: egoPri setMotion: MoveTo theX theY self)
			)
			(1
				(ego
					view: 40
					setLoop: 1
					cel: 0
					x: theX
					y: theY
					setCycle: EndLoop self
				)
				(if (ego looper?)
					((ego looper?) dispose:)
					(ego looper: 0)
				)
				(if local40
					(HandsOn)
					(self dispose:)
				)
			)
			(2
				(welcomeWoman setMotion: MoveTo 5 142 self)
			)
			(3
				(welcomeWoman setMotion: PolyPath toX toY self)
			)
			(4
				(cond 
					((and (not welcomeWomanHere) (InnTst SURVIVOR_WILL_COME))
						(= welcomeWomanHere TRUE)
						(messager say: N_WELCOME_WOMAN V_DOIT C_SURVIVOR 0 self)
					)
					((and (not welcomeWomanHere) (InnTst SURVIVOR_DEAD))
						(= welcomeWomanHere TRUE)
						(messager say: N_WELCOME_WOMAN V_DOIT C_SURVIVOR_DIED 0 self)
					)
					(else
						(= msgOfTheDay (DailyMsg N_WELCOME_WOMAN V_DOIT C_HELLO))
						(messager
							say: N_WELCOME_WOMAN V_DOIT C_HELLO (+ (mod (+ Day msgOfTheDay) msgOfTheDay) 1) self
						)
					)
				)
			)
			(5
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance standNorth of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local7 0)
				(if
					(and
						(CueObj client?)
						((CueObj client?) approachX?)
						(!= (theIconBar curIcon?) (theIconBar at: ICON_WALK))
					)
					(= local7 1)
				)
				(self cue:)
			)
			(1
				(HandsOff)
				(if local9
					(if flirted
						(self setScript: welcomeFlirtExit self)
						(if (InnTst SURVIVOR_WILL_COME)
							(curRoom setScript: survivorWalkIn)
						)
					else
						(if (InnTst SURVIVOR_WILL_COME)
							(curRoom setScript: survivorWalkIn)
						)
						(self setScript: welcomeExit self)
					)
					(ego setCycle: BegLoop)
				else
					(ego setCycle: BegLoop self)
				)
				(theFeature approachVerbs: V_DO)
			)
			(2
				(if local9
					(welcomeWoman setMotion: MoveTo -10 142)
					(= local9 0)
				)
				(ego
					normalize: 5
					cel: 0
					setMotion: MoveTo (ego x?) (- (ego y?) 5) self
				)
			)
			(3
				(if local7
					(ego
						setPri: -1
						setMotion:
							PolyPath
							((CueObj client?) approachX?)
							((CueObj client?) approachY?)
							CueObj
					)
				else
					(ego setPri: -1 setMotion: PolyPath eventX eventY self)
				)
				(welcomeWoman stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance getFood of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (InnTst ORDERED_FOOD))
					(InnSet ORDERED_FOOD)
					(= innDay Day)
				)
				(HandsOff)
				(messager say: N_EGO_TELL V_TELL C_ORDER 0 self)
			)
			(1
				(if flirted
					(self setScript: welcomeFlirtExit self)
				else
					(self setScript: welcomeExit self)
				)
				(if (InnTst SURVIVOR_WILL_COME)
					(ego setScript: survivorWalkIn)
				)
			)
			(2
				(= seconds 4)
			)
			(3
				(welcomeWoman view: 304 setMotion: MoveTo 5 142 self)
			)
			(4
				(welcomeWoman setMotion: PolyPath toX toY self)
			)
			(5
				(welcomeWoman
					view: 303
					loop: 4
					cel: 0
					setCycle: CycleTo 4 1 self
				)
			)
			(6
				(tray
					x: (welcomeWoman x?)
					y: (+ (welcomeWoman y?) 30)
					z: 30
					priority: (+ egoPri 3)
					addToPic:
				)
				(welcomeWoman setCycle: EndLoop self)
			)
			(7
				(messager say: N_WELCOME_WOMAN V_DOIT C_FOOD 0 self)
			)
			(8
				(cond 
					((== ((inventory at: iRoyals) message?) V_DINARS)
						(messager say: N_WELCOME_WOMAN V_DOIT C_WRONG_MONEY 0 self)
						(= triedToPayInDinars TRUE)
					)
					(((inventory at: iRoyals) amount?)
						((inventory at: iRoyals)
							amount: (- ((inventory at: iRoyals) amount?) 1)
						)
						(messager say: N_WELCOME_WOMAN V_DOIT C_PAY 0 self)
					)
					(else
						(messager say: N_WELCOME_WOMAN V_DOIT C_NO_MONEY 0 self)
					)
				)
			)
			(9
				(welcomeWoman
					view: 301
					setCycle: StopWalk -1
					setMotion: PolyPath 5 142 self
				)
			)
			(10
				(welcomeWoman setMotion: MoveTo -10 142 self)
			)
			(11
				(messager say: NULL V_DOIT C_EAT_MEAL 0 self)
			)
			(12
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance tellFit of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 5)
			)
			(1
				(messager say: N_SURVIVOR V_DOIT C_SURVIVOR_FIT 0 self)
			)
			(2 (= cycles 15))
			(3
				(welcomeWoman setMotion: MoveTo 5 142 self)
			)
			(4
				(messager say: N_WELCOME_WOMAN V_DOIT C_NO_MORE_QUESTIONS 0 self)
			)
			(5
				(welcomeWoman setMotion: MoveTo -15 142 self)
			)
			(6
				(survivor setCycle: EndLoop)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance welcomeExit of Script
	
	(method (changeState newState &tmp [temp0 20])
		(switch (= state newState)
			(0
				(if (not caller)
					(HandsOff)
				)
				(welcomeWoman
					view: 301
					setCycle: StopWalk -1
					setMotion: PolyPath 5 142 self
				)
			)
			(1
				(welcomeWoman setMotion: MoveTo -10 142 self)
				(= flirted FALSE)
				(= local9 0)
			)
			(2
				(welcomeWoman stopUpd:)
				(if (not caller)
					(HandsOn)
				)
				(self dispose:)
			)
		)
	)
)

(instance welcomeFlirtExit of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not caller)
					(HandsOff)
				)
				(welcomeWoman
					view: 301
					setCycle: StopWalk -1
					setMotion: PolyPath 75 152 self
				)
			)
			(1
				(welcomeWoman
					view: 303
					loop: (+ (Random 0 1) 2)
					cel: 0
					setCycle: BegLoop self
				)
			)
			(2
				(welcomeWoman
					view: 301
					setCycle: StopWalk -1
					setMotion: PolyPath 5 142 self
				)
			)
			(3
				(welcomeWoman setMotion: MoveTo -10 142 self)
				(= flirted FALSE)
				(= local9 0)
			)
			(4
				(welcomeWoman stopUpd:)
				(if (not caller)
					(HandsOn)
				)
				(self dispose:)
			)
		)
	)
)

(instance egoActions of Actions

	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_WALK
					(cond 
						((curRoom script?) 0)
						((== (ego view?) 40)
							(= eventX ((User curEvent?) x?))
							(= eventY ((User curEvent?) y?))
							(ego setScript: standNorth)
							(return TRUE)
						)
						(else
							(super doVerb: theVerb)
						)
					)
				)
				(else
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance egoTell of Teller

	(method (showDialog)
		(return
			(if (or local9 local40)
				(super
					showDialog:
						1
						local9
						-60
						local9
						64
						local9
						-6
						local9
						69
						(if local40 (not survivorWontTalk) else 0)
						68
						local40
						-70
						local9
				)
			else
				(client doVerb: V_TALK)
				(return -999)
			)
		)
	)
	
	(method (doChild param1 &tmp temp0)
		(return
			(cond 
				((== param1 -60)
					(if flirted
						(curRoom setScript: welcomeFlirtExit)
					else
						(curRoom setScript: welcomeExit)
					)
					(if
					(and (not (InnTst SURVIVOR_WAS_HERE)) (InnTst SURVIVOR_WILL_COME))
						(ego setScript: survivorWalkIn)
					)
					(return TRUE)
				)
				((== param1 -70)
					(cond 
						((Btst fStarving)
							(Bclr fStarving)
						)
						((Btst fHungry)
							(Bclr fHungry)
						)
						((< freeMeals 2)
							(++ freeMeals)
						)
						((not (InnTst SURVIVOR_WILL_COME))
							(= survivorIsHere TRUE)
						)
					)
					(if (not survivorIsHere)
						(curRoom setScript: getFood)
						(if (and (not (InnTst SURVIVOR_WAS_HERE)) (InnTst SURVIVOR_WILL_COME))
							(ego setScript: survivorWalkIn)
						)
						(= survivorIsHere TRUE)
					else
						(messager say: NULL V_DOIT C_TOO_FULL)
					)
					(return FALSE)
				)
				((== param1 -6)
					(= flirted TRUE)
					(= temp0 (DailyMsg N_WELCOME_WOMAN V_DOIT C_FLIRT))
					(messager say: N_WELCOME_WOMAN V_DOIT C_FLIRT (+ (mod (+ Day temp0) temp0) 1))
					(return FALSE)
				)
				(else
					(super doChild: param1 &rest)
				)
			)
		)
	)
)

(instance survivor of Actor
	(properties
		x -15
		y 189
		noun N_SURVIVOR
		view 307
		loop 2
		cel 2
	)
)

(instance survivorTell of Teller

	(method (showDialog)
		(return
			(if (not survivorWontTalk)
				(super showDialog:)
			else
				(messager say: N_SURVIVOR V_DOIT C_NO_RESPONSE)
				(return -999)
			)
		)
	)
	
	(method (doChild param1)
		(return
			(cond 
				((== param1 -17)
					(ego solvePuzzle: fAskAboutPeaceMission 7)
					(if (== (|= survivorTalkCount $0001) 1023)
						(= survivorWontTalk TRUE)
						(curRoom setScript: tellFit)
					)
					(super doChild: param1)
				)
				((== param1 -46)
					(if (== (|= survivorTalkCount $0002) 1023)
						(= survivorWontTalk TRUE)
						(curRoom setScript: tellFit)
					)
					(super doChild: param1)
				)
				((== param1 -49)
					(if (== (|= survivorTalkCount $0004) 1023)
						(= survivorWontTalk TRUE)
						(curRoom setScript: tellFit)
					)
					(super doChild: param1)
				)
				((== param1 -52)
					(if (== (|= survivorTalkCount $0008) 1023)
						(= survivorWontTalk TRUE)
						(curRoom setScript: tellFit)
					)
					(super doChild: param1)
				)
				((== param1 -47)
					(if (== (|= survivorTalkCount $0010) 1023)
						(= survivorWontTalk TRUE)
						(curRoom setScript: tellFit)
					)
					(super doChild: param1)
				)
				((== param1 -66)
					(if (== (|= survivorTalkCount $0020) 1023)
						(= survivorWontTalk TRUE)
						(curRoom setScript: tellFit)
					)
					(return TRUE)
				)
				((== param1 -48)
					(if (== (|= survivorTalkCount $0040) 1023)
						(= survivorWontTalk TRUE)
						(curRoom setScript: tellFit)
					)
					(return TRUE)
				)
				((== param1 -50)
					(if (== (|= survivorTalkCount $0080) 1023)
						(= survivorWontTalk TRUE)
						(curRoom setScript: tellFit)
					)
					(return TRUE)
				)
				((== param1 -51)
					(if (== (|= survivorTalkCount $0100) 1023)
						(= survivorWontTalk TRUE)
						(curRoom setScript: tellFit)
					)
					(return TRUE)
				)
				((== param1 -65)
					(if (== (|= survivorTalkCount $0200) 1023)
						(= survivorWontTalk TRUE)
						(curRoom setScript: tellFit)
					)
					(return TRUE)
				)
				(else (super doChild: param1))
			)
		)
	)
)

(instance survivorTalker of GloryTalker
	(properties
		x 1
		y 10
		view 308
		loop 1
		talkWidth 150
		back 57
		textX 137
		textY 3
		backColor 27
	)
	
	(method (init)
		(super
			init: survivorBrow survivorEyes survivorMouth &rest
		)
	)
)

(instance survivorEyes of Prop
	(properties
		nsTop 24
		nsLeft 62
		view 308
		loop 2
	)
)

(instance survivorBrow of View
	(properties
		nsTop 16
		nsLeft 40
		view 308
		loop 3
	)
)

(instance survivorMouth of Prop
	(properties
		nsTop 37
		nsLeft 42
		view 308
	)
)

(instance welcomeWoman of Actor
	(properties
		x -20
		y 145
		noun N_WELCOME_WOMAN
		view 301
	)
)

(instance welcomeTalker of GloryTalker
	(properties
		x 1
		y 10
		view 302
		loop 1
		talkWidth 150
		back 57
		textX 137
		textY 3
		backColor 14
	)
	
	(method (init)
		(super init: welcomeBrow welcomeEyes welcomeMouth &rest)
	)
)

(instance welcomeEyes of Prop
	(properties
		nsTop 27
		nsLeft 45
		view 302
		loop 2
	)
)

(instance welcomeBrow of Prop
	(properties
		nsTop 27
		nsLeft 43
		view 302
		loop 3
	)
)

(instance welcomeMouth of Prop
	(properties
		nsTop 43
		nsLeft 48
		view 302
	)
)

(instance welcomeTell of Teller
	
	(method (showDialog)
		(super
			showDialog:
				-2
				(cond 
					((InnTst (| SECOND_TIME SURVIVOR_WILL_COME)))
					((InnTst ASKED_ABOUT_RESTAURANT) (not (InnTst SURVIVOR_DEAD)))
				)
				-38
				(InnTst SURVIVOR_DEAD)
				7
				triedToPayInDinars
				-19
				(InnTst FIRST_TIME)
				-4
				(InnTst SECOND_TIME)
				44
				(InnTst SURVIVOR_DEAD)
				33
				(InnTst FIRST_TIME)
				-12
				(InnTst FIRST_TIME)
				-3
				(InnTst (| SECOND_TIME SURVIVOR_WILL_COME))
				-40
				(InnTst SURVIVOR_DEAD)
				58
				(if (InnTst SURVIVOR_WAS_HERE)
					(if (not (InnTst SURVIVOR_DEAD))
						(not (InnSet SURVIVOR_DONE))
					)
				else
					0
				)
				-34
				(InnTst SURVIVOR_WILL_COME)
				37
				(InnTst SURVIVOR_DEAD)
				-26
				(InnTst FIRST_TIME)
				-5
				(InnTst (| SECOND_TIME SURVIVOR_WILL_COME))
		)
	)
	
	(method (getSeqNum param1 &tmp temp0)
		(return
			(if (== param1 63)
				(return
					(+
						(mod (+ Day (= temp0 (DailyMsg N_WELCOME_WOMAN V_TALK C_DAILY))) temp0)
						1
					)
				)
			else
				(return TRUE)
			)
		)
	)
	
	(method (doChild param1 &tmp temp0)
		(return
			(cond 
				((== param1 -4)
					(= temp0 (DailyMsg N_WELCOME_WOMAN V_TELL C_JANNA))
					(messager say: N_WELCOME_WOMAN V_TELL C_JANNA (+ (mod (+ Day temp0) temp0) 1))
					(return FALSE)
				)
				((== param1 -3)
					(= temp0 (DailyMsg N_WELCOME_WOMAN V_TELL C_RUMORS))
					(messager say: N_WELCOME_WOMAN V_TELL C_RUMORS (+ (mod (+ Day temp0) temp0) 1))
					(super doChild: param1)
					(return FALSE)
				)
				((== param1 -62)
					(= temp0 (DailyMsg N_WELCOME_WOMAN V_TELL C_GOSSIP))
					(messager say: N_WELCOME_WOMAN V_TELL C_GOSSIP (+ (mod (+ Day temp0) temp0) 1))
					(return FALSE)
				)
				((== param1 -2)
					(= temp0 (DailyMsg N_WELCOME_WOMAN V_TELL C_MENU))
					(messager say: N_WELCOME_WOMAN V_TELL C_MENU (+ (mod (+ Day temp0) temp0) 1))
					(super doChild: param1)
					(return FALSE)
				)
				((== param1 -9)
					(= temp0 (DailyMsg N_WELCOME_WOMAN V_TELL C_FOOD))
					(messager say: N_WELCOME_WOMAN V_TELL C_FOOD (+ (mod (+ Day temp0) temp0) 1))
					(return FALSE)
				)
				((== param1 -5)
					(= temp0 (DailyMsg N_WELCOME_WOMAN V_TELL C_TARNA))
					(messager say: N_WELCOME_WOMAN V_TELL C_TARNA (+ (mod (+ Day temp0) temp0) 1))
					(super doChild: param1)
					(return FALSE)
				)
				((== param1 -63)
					(= temp0 (DailyMsg N_WELCOME_WOMAN V_TELL C_DAILY))
					(messager say: N_WELCOME_WOMAN V_TELL C_DAILY (+ (mod (+ Day temp0) temp0) 1))
					(return FALSE)
				)
				((== param1 -12)
					(InnSet ASKED_ABOUT_RESTAURANT)
					(super doChild: param1)
					(return TRUE)
				)
				(else (super doChild: param1))
			)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb V_ROYALS)
				(cond 
					((Btst fStarving)
						(Bclr fHungry)
					)
					((Btst fHungry)
						(Bclr fHungry)
					)
					((< freeMeals 2)
						(++ freeMeals)
					)
					((not (InnTst SURVIVOR_WILL_COME))
						(= survivorIsHere TRUE)
					)
				)
				(if (not survivorIsHere)
					(curRoom setScript: getFood)
					(if (and (not (InnTst SURVIVOR_WAS_HERE)) (InnTst SURVIVOR_WILL_COME))
						(ego setScript: survivorWalkIn)
					)
					(= survivorIsHere TRUE)
				else
					(messager say: NULL V_DOIT C_TOO_FULL)
				)
				(return TRUE)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance flameLeft of Prop
	(properties
		x 16
		y 162
		noun N_FLAME
		view 300
		loop 1
		cel 2
		priority 14
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
)

(instance flameCenter of Prop
	(properties
		x 148
		y 162
		noun N_FLAME
		view 300
		loop 1
		priority 14
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
)

(instance flameRight of Prop
	(properties
		x 274
		y 154
		noun N_FLAME
		view 300
		loop 1
		priority 12
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
)

(instance leftDoor of View
	(properties
		x 31
		y 45
		noun N_LEFTDOOR
		view 300
		loop 4
		signal (| ignrAct skipCheck fixPriOn stopUpdOn)
	)
	
	(method (doit &tmp thePal theCel)
		(if (= thePal (Abs (PalVary PALVARYINFO)))
			(if
				(!=
					(= theCel
						(cond 
							((and (<= 0 thePal) (<= thePal 5)) 0)
							((and (<= 6 thePal) (<= thePal 10)) 1)
							((and (<= 11 thePal) (<= thePal 15)) 2)
							((and (<= 16 thePal) (<= thePal 20)) 3)
							((and (<= 21 thePal) (<= thePal 25)) 4)
							((and (<= 26 thePal) (<= thePal 30)) 5)
							((and (<= 31 thePal) (<= thePal 35)) 6)
							((and (<= 36 thePal) (<= thePal 40)) 7)
							((and (<= 41 thePal) (<= thePal 45)) 8)
							((and (<= 46 thePal) (<= thePal 50)) 9)
							((and (<= 51 thePal) (<= thePal 55)) 10)
							((and (<= 56 thePal) (<= thePal 64)) 11)
						)
					)
					cel
				)
				(self setCel: theCel stopUpd:)
			)
		)
		(super doit:)
	)
)

(instance rightDoor of View
	(properties
		x 156
		y 45
		noun N_RIGHTDOOR
		view 300
		loop 5
		signal (| ignrAct skipCheck fixPriOn stopUpdOn)
	)
	
	(method (doit &tmp thePal theCel)
		(if (= thePal (Abs (PalVary PALVARYINFO)))
			(if
				(!=
					(= theCel
						(cond 
							((and (<= 0 thePal) (<= thePal 5)) 0)
							((and (<= 6 thePal) (<= thePal 10)) 1)
							((and (<= 11 thePal) (<= thePal 15)) 2)
							((and (<= 16 thePal) (<= thePal 20)) 3)
							((and (<= 21 thePal) (<= thePal 25)) 4)
							((and (<= 26 thePal) (<= thePal 30)) 5)
							((and (<= 31 thePal) (<= thePal 35)) 6)
							((and (<= 36 thePal) (<= thePal 40)) 7)
							((and (<= 41 thePal) (<= thePal 45)) 8)
							((and (<= 46 thePal) (<= thePal 50)) 9)
							((and (<= 51 thePal) (<= thePal 55)) 10)
							((and (<= 56 thePal) (<= thePal 64)) 11)
						)
					)
					cel
				)
				(self setCel: theCel stopUpd:)
			)
		)
		(super doit:)
	)
)

(instance tray of View
	(properties
		noun N_TRAY
		view 300
		loop 3
		priority 14
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance otherTray of View
	(properties
		x 250
		y 200
		z 41
		noun N_TRAY
		view 300
		loop 3
		priority 13
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance tray1 of View
	(properties
		x 1
		y 200
		z 37
		noun N_TRAY
		view 300
		loop 3
		priority 14
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance tray2 of View
	(properties
		x 28
		y 200
		z 34
		noun N_TRAY
		view 300
		loop 3
		priority 14
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance tray3 of View
	(properties
		x 107
		y 200
		z 31
		noun N_TRAY
		view 300
		loop 3
		priority 14
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance tray4 of View
	(properties
		x 163
		y 200
		z 31
		noun N_TRAY
		view 300
		loop 3
		priority 14
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance tray5 of View
	(properties
		x 225
		y 200
		z 38
		noun N_TRAY
		view 300
		loop 3
		priority 13
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance tray6 of View
	(properties
		x 279
		y 200
		z 55
		noun N_TRAY
		view 306
		loop 4
		priority 13
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance northSitter1 of View
	(properties
		x 15
		y 155
		view 306
		cel 2
		priority 10
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(messager say: N_SITTERS V_DO NULL)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance northSitter2 of View
	(properties
		x 150
		y 154
		view 306
		cel 1
		priority 10
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(messager say: N_SITTERS V_DO NULL)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance northSitter3 of View
	(properties
		x 266
		y 150
		view 306
		priority 9
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(messager say: N_SITTERS V_DO NULL)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance eastSitter1 of View
	(properties
		x 58
		y 165
		view 306
		loop 3
		cel 3
		priority 11
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(messager say: N_SITTERS V_DO NULL)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance eastSitter2 of View
	(properties
		x 200
		y 169
		view 306
		loop 3
		cel 1
		priority 12
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(messager say: N_SITTERS V_DO NULL)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance eastSitter3 of View
	(properties
		x 317
		y 158
		view 306
		loop 3
		cel 1
		priority 10
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(messager say: N_SITTERS V_DO NULL)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance westSitter1 of View
	(properties
		x 107
		y 171
		view 306
		loop 2
		cel 3
		priority 12
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(messager say: N_SITTERS V_DO NULL)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance westSitter2 of View
	(properties
		x 225
		y 159
		view 306
		loop 2
		priority 10
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(messager say: N_SITTERS V_DO NULL)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance board of Feature
	(properties
		x 113
		y 101
		nsTop 91
		nsLeft 98
		nsBottom 113
		nsRight 133
		sightAngle 40
		approachX 113
		approachY 126
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(ego solvePuzzle: fReadBoard 3)
			(curRoom newRoom: 305)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance northCushion2 of Feature
	(properties
		x 149
		y 154
		noun N_CUSHION
		nsTop 146
		nsLeft 132
		nsBottom 155
		nsRight 165
		sightAngle 40
		approachX 164
		approachY 146
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(if (!= (ego view?) 40)
				(= theX x)
				(= theY y)
				(= toX 132)
				(= toY 165)
				(= egoPri 11)
				(= theFeature self)
				(ego setScript: sitNorth)
				(self approachVerbs:)
			else
				(super doVerb: theVerb &rest)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance northCushion3 of Feature
	(properties
		x 272
		y 148
		noun N_CUSHION
		nsTop 140
		nsLeft 245
		nsBottom 146
		nsRight 281
		sightAngle 40
		approachX 252
		approachY 139
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(if (!= (ego view?) 40)
				(= theX x)
				(= theY y)
				(= toX 252)
				(= toY 159)
				(= egoPri 9)
				(= theFeature self)
				(ego setScript: sitNorth)
				(self approachVerbs:)
			else
				(super doVerb: theVerb &rest)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance westCushion of Feature
	(properties
		x 103
		y 167
		noun N_SURVIVOR_CUSHION
		nsTop 160
		nsLeft 93
		nsBottom 174
		nsRight 113
		sightAngle 40
	)
)

(instance chandelier of Feature
	(properties
		x 114
		y 14
		noun N_CHANDELIER
		nsTop -1
		nsLeft 86
		nsBottom 30
		nsRight 143
		sightAngle 180
	)
)

(instance rightchandelier of Feature
	(properties
		x 239
		y 20
		noun N_CHANDELIER
		nsLeft 214
		nsBottom 41
		nsRight 264
		sightAngle 180
	)
)

(instance lefttable of Feature
	(properties
		x 27
		y 159
		noun N_LEFTTABLE
		nsTop 149
		nsBottom 169
		nsRight 55
		sightAngle 180
	)
)

(instance midtable of Feature
	(properties
		x 152
		y 162
		noun N_MIDTABLE
		nsTop 155
		nsLeft 110
		nsBottom 173
		nsRight 194
		sightAngle 180
	)
)

(instance righttable of Feature
	(properties
		x 272
		y 155
		noun N_RIGHTTABLE
		nsTop 146
		nsLeft 232
		nsBottom 164
		nsRight 313
		sightAngle 180
	)
)

(instance stairs of Feature
	(properties
		x 290
		y 58
		noun N_STAIRS
		nsTop 39
		nsLeft 275
		nsBottom 78
		nsRight 305
		sightAngle 180
	)
)
