;;; Sierra Script 1.0 - (do not remove this comment)
(script# 39)
(include game.sh) (include "39.shm")
(use Main)
(use Skilled)
(use Teller)
(use Procs)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm39 0
	swordMusic 1
	theMaster 2
	comeBackLittleEgo 3
	masterTalker 4
)

(local
	saveSortedFeatures
	masterAttention
	local2	;unused
	askedFight
	canFightMaster
	sleepCued
	masterTellMainBranch = [
		STARTTELL
		-42		;C_WEAPONMASTER
		-34		;C_FIGHTING
		-32		;C_CASTLE
		C_TOWN2
		ENDTELL
		]
	masterTell1 = [
		STARTTELL
		C_SWORD
		C_SKILLS
		C_PRACTICE
		C_FRIENDSHIP
		ENDTELL
		]
	masterTell2 = [
		STARTTELL
		C_SWORD
		C_ARMOR
		C_PRACTICE
		C_BRIGANDS
		ENDTELL
		]
	masterTell3 = [
		STARTTELL
		C_BARON
		C_BARNARD
		C_ELSA
		C_GUARDS
		ENDTELL
		]
	[masterTellTree 6]
	masterTellKeys = [
		STARTTELL
		-42		;C_WEAPONMASTER
		-34		;C_FIGHTING
		-32		;C_CASTLE
		ENDTELL
		]
)
(procedure (DeclineTraining)
	(messager say: N_ROOM NULL C_DECLINE)
)

(procedure (StartLesson)
	(if (not [egoStats PARRY])
		(messager say: N_ROOM NULL C_NO_PARRY)
	else
		(= askedFight FALSE)
		(if (GiveMoney 10)
			(if (or (not (ego has: iSword)) (not (ego has: iShield)))
				(messager say: N_ROOM NULL C_LOANER)
			else
				(messager say: N_ROOM NULL C_PAYUP)
			)
			(= masterDay Day)
			(SolvePuzzle f39FightMaster 3 FIGHTER)
			((ScriptID 39 0) setScript: (ScriptID 222 2))
		else
			(messager say: N_ROOM NULL C_NO_MONEY 1 theMaster)
		)
	)
)

(class WeaponMaster of SkilledActor
	(properties
		strength 40
		intell 45
		agil 45
		vit 35
		luck 45
		weap 50
		parry 50
		dodge 40
		armorValue 3
		shieldValue 3
		weapValue 5
		warriorX 187
		warriorY 135
		endFight 0
	)
	
	(method (getHit)
		(if fightLeft
			(self x: (+ (self x?) 2))
			((self opponent?) x: (+ ((self opponent?) x?) 2))
		else
			(self x: (- (self x?) 2))
			((self opponent?) x: (- ((self opponent?) x?) 2))
		)
	)
	
	(method (gotBeat theScript)
		(self endFight: TRUE)
		(self setScript: theScript)
	)
)

(instance swordMusic of Sound)

(instance rm39 of Room
	(properties
		noun N_ROOM
		picture 39
		horizon 100
	)
	
	(method (init &tmp egoY temp1)
		(= [masterTellTree 0] @masterTellMainBranch)
		(= [masterTellTree 1] @masterTell1)
		(= [masterTellTree 2] @masterTell2)
		(= [masterTellTree 3] @masterTell3)
		(= [masterTellTree 4] ENDTELL)
		(Load RES_VIEW 39)
		(if
			(and
				(< TIME_DAWN timeODay)
				(< timeODay TIME_SUNSET)
				(not (Btst fBeatMaster))
				(!= masterDay Day)
			)
			(LoadMany RES_SCRIPT 212 222 223 224 217 220 218 216)
			(LoadMany RES_VIEW 639 638 501 503 514)
		)
		(switch prevRoomNum
			(38 (self style: SCROLLLEFT))
			(40 (self style: SCROLLRIGHT))
			(41 (self style: WIPEUP))
			(else  (self style: WIPEDOWN))
		)
		(super init: &rest)
		(= saveSortedFeatures useSortedFeatures)
		(= useSortedFeatures FALSE)
		(SolvePuzzle f39EnterCourtyard 1)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 108
						94 98
						91 113
						74 118
						76 135
						40 141
						28 122
						0 119
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						319 119
						300 120
						269 106
						238 107
						208 90
						319 95
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						62 189
						64 172
						104 171
						137 189
					yourself:
				)
		)
		(= egoY (ego y?))
		(= egoX (ego x?))
		(= yesNoTimer 0)
		(switch prevRoomNum
			(38
				(NormalEgo)
				(ego posn: 15 egoY init:)
			)
			(40
				(NormalEgo)
				(ego posn: 304 egoY init:)
			)
			(41
				(NormalEgo)
				(cond 
					((< egoX 100)
						(= egoX 100)
					)
					((> egoX 215)
						(= egoX 215)
					)
				)
				(ego posn: egoX 102 init: setMotion: MoveTo egoX 107)
			)
			(else 
				(NormalEgo)
				(ego setScript: egoEnters init:)
			)
		)
		(if (not Night)
			(rightGuard init: setPri: 2 stopUpd:)
			(leftGuard init: setPri: 2 stopUpd:)
		)
		(self
			setFeatures:
				castle
				barrels
				waggon
				curbLeft
				curbRite
				wall
				court
		)
		;UPGRADE
;;;			(castle
;;;				init:
;;;				setOnMeCheck: ftrControl cBLUE
;;;			)
;;;			(barrels
;;;				init:
;;;				setOnMeCheck: ftrControl cGREEN
;;;			)
;;;			(waggon
;;;				init:
;;;				setOnMeCheck: ftrControl cLCYAN
;;;			)
;;;			(curbLeft
;;;				init:
;;;				setOnMeCheck: ftrControl cMAGENTA
;;;			)
;;;			(curbRite
;;;				init:
;;;				setOnMeCheck: ftrControl cLMAGENTA
;;;			)
;;;			(wall
;;;				init:
;;;				setOnMeCheck: ftrControl cBROWN
;;;			)
;;;			(court
;;;				init:
;;;				setOnMeCheck: ftrControl cGREY
;;;			)
		
		(if
			(and
				(< TIME_DAWN timeODay)
				(< timeODay TIME_SUNSET)
				(not (Btst fBeatMaster))
				(!= masterDay Day)
			)
			(cond 
				((Btst fMasterIsHere)
					(masterTeller
						init: (ScriptID 39 2) @masterTellMainBranch @masterTellTree @masterTellKeys
					)
					((ScriptID 39 2) actions: masterTeller init:)
					(self setScript: (ScriptID 222 1))
				)
				((> (Random 0 100) 50)
					(masterTeller
						init: (ScriptID 39 2) @masterTellMainBranch @masterTellTree @masterTellKeys
					)
					((ScriptID 39 2)
						actions: masterTeller
						setCycle: Walk
						init:
					)
					(Bset fMasterIsHere)
					(self setScript: (ScriptID 222 0))
				)
			)
			(if [egoStats PARRY]
				(= canFightMaster TRUE)
			)
		)
	)
	
	(method (doit)
		;added to address timer bug
		(if (< (Abs (- gameTime name)) 1) (return))
		(= name gameTime)
		(super doit:)
		(cond 
			((ego script?))
			((ego edgeHit?)
				(ego setScript: egoExits)
			)
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(= useSortedFeatures saveSortedFeatures)
		(if (>= Clock 3000)
			(Bclr fMasterIsHere)
		)
		(LoadMany FALSE 130 212 222 223 224 220 218 216 217)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME (messager say: N_ROOM 59))
			(V_ROCK (messager say: N_ROOM 59))
			(V_DAGGER (messager say: N_ROOM 59))
			(V_SLEEP
				(if Night
					(= sleepCued TRUE)
					(messager say: N_ROOM V_SLEEP C_NIGHT 0 curRoom)
				else
					(messager say: N_ROOM V_SLEEP C_DAY)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(if sleepCued
			(= sleepCued FALSE)
			(if (and (< 750 Clock) (< Clock 2550))
				(FixTime 21)
			)
			(curRoom setScript: goTo37)
		else
			(SolvePuzzle f39FightMaster 3 FIGHTER)
			((ScriptID 39 0) setScript: (ScriptID 222 2))
		)
	)
)

(instance castle of Feature
	(properties
		x 150
		y 30
		noun N_CASTLE
		nsTop 1
		nsLeft 1
		nsBottom 50
		nsRight 250
		onMeCheck cBLUE
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_SLEEP)
			(rm39 doVerb: theVerb)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance barrels of Feature
	(properties
		x 21
		y 109
		noun N_BARRELS
		nsTop 101
		nsLeft 3
		nsBottom 114
		nsRight 34
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_SLEEP)
			(rm39 doVerb: theVerb)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance waggon of Feature
	(properties
		x 27
		y 110
		noun N_WAGON
		onMeCheck cLCYAN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_SLEEP)
			(rm39 doVerb: theVerb)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance curbLeft of Feature
	(properties
		x 59
		y 110
		noun N_CURB_LEFT
		nsTop 113
		nsLeft 43
		nsBottom 132
		nsRight 76
		onMeCheck cMAGENTA
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_SLEEP)
			(rm39 doVerb: theVerb)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance curbRite of Feature ;EO: Okay, maybe the incorrect spellings were jokes after all.
	(properties
		x 263
		y 93
		noun N_CURB_RIGHT
		onMeCheck cLMAGENTA
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_SLEEP)
			(rm39 doVerb: theVerb)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance wall of Feature
	(properties
		x 52
		y 181
		noun N_WALL
		nsTop 175
		nsLeft 81
		nsBottom 187
		nsRight 101
		onMeCheck cBROWN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_SLEEP)
			(rm39 doVerb: theVerb)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance court of Feature
	(properties
		x 185
		y 132
		noun N_COURT
		nsTop 120
		nsLeft 123
		nsBottom 146
		nsRight 251
		onMeCheck cGREY
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_SLEEP)
			(rm39 doVerb: theVerb)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance rightGuard of View
	(properties
		x 164
		y 60
		noun N_RIGHT_GUARD
		view 39
		loop 1
		priority 15
		signal fixPriOn
	)
	;added unused message
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(messager say: N_RIGHT_GUARD V_ALTTALK2)
			)
			(else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance leftGuard of View
	(properties
		x 141
		y 61
		noun N_LEFT_GUARD
		view 39
		priority 15
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(rightGuard doVerb: theVerb)
	)
)

(instance egoEnters of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego posn: 175 240 setMotion: MoveTo 160 180 self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoExits of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(switch (ego edgeHit?)
					(NORTH
						(HandsOn)
						(curRoom newRoom: 41)
					)
					(SOUTH
						(ego setMotion: MoveTo (+ (ego x?) 15) 240 self)
					)
					(WEST
						(ego setMotion: PolyPath -20 (ego y?) self)
					)
					(EAST
						(ego setMotion: PolyPath 337 (ego y?) self)
					)
				)
			)
			(1
				(HandsOn)
				(switch (ego edgeHit?)
					(SOUTH
						(if Night
							(curRoom setScript: goTo37)
						else
							(curRoom newRoom: 37)
						)
					)
					(WEST
						(curRoom newRoom: 38)
					)
					(EAST
						(curRoom newRoom: 40)
					)
				)
			)
		)
	)
)

(instance comeBackLittleEgo of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_ROOM 62 C_GET_OVER_HERE 0 self)
			)
			(1
				(HandsOff)
				(theMaster view: 639 cel: 0)
				(if (< (ego x?) ((ScriptID 39 2) x?))
					(theMaster loop: 5)
				else
					(theMaster loop: 4)
				)
				(ego
					ignoreActors:
					setMotion: MoveTo
						(if (< (ego x?) ((ScriptID 39 2) x?))
							(- (theMaster x?) 40)
						else
							(+ (theMaster x?) 40)
						)
						(theMaster y?)
						self
				)
			)
			(2
				(Face ego theMaster)
				(= masterAttention TRUE)
				(ego ignoreActors: 0)
				(client setScript: 0)
				(HandsOn)
			)
		)
	)
)

(instance goTo37 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if Night
					(if (< [egoStats CLIMB] 10)
						(messager say: N_ROOM NULL C_NO_CLIMB 1 self)
					else
						(messager say: N_ROOM NULL C_CLIMB_OVER 1 self)
					)
				else
					(self cue:)
				)
			)
			(1
				(if (< [egoStats CLIMB] 10)
					(ego setMotion: PolyPath (ego x?) 172 self)
				else
					(curRoom newRoom: 37)
				)
			)
			(2
				(HandsOn)
				(ego setScript: 0)
				(self dispose:)
			)
		)
	)
)

(instance teacherTalk of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(if (not [egoStats PARRY])
					(messager say: N_ROOM NULL C_NO_PARRY)
				else
					(Bset fOfferedTraining)
					(messager say: N_ROOM NULL C_PRACTICE)
					(= yesNoTimer 130)
					(= askedFight TRUE)
				)
				(= seconds 5)
			)
			(2 (self dispose:))
		)
	)
)

(instance theMaster of WeaponMaster
	(properties
		noun N_MASTER
		view 639
	)
	
	(method (init)
		(= nightPalette 1639)
		(PalVary PALVARYTARGET 1639)
		(AssertPalette 639)
		(super init:)
	)
	
	(method (doit)
		;added to address timer bug
		(if (< (Abs (- gameTime name)) 1) (return))
		(= name gameTime)
		(cond 
			((> yesNoTimer 1)
				(-- yesNoTimer)
			)
			((and (== yesNoTimer 1) askedFight)
				(= yesNoTimer 0)
				(= askedFight FALSE)
				(DeclineTraining)
			)
		)
		(super doit:)
	)
	
	(method (cue)
		(DeclineTraining)
	)
)

(instance masterTeller of Teller
	(method (showDialog &tmp superShowDialog)
		(if (== (= superShowDialog (super showDialog:)) -34)
			(curRoom setScript: teacherTalk)
		)
		(return superShowDialog)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(if (not (< (ego distanceTo: (ScriptID 39 2)) 45))
					(rm39 setScript: comeBackLittleEgo)
				else
					(SolvePuzzle f39TalkToMaster 1)
					(super doVerb: theVerb &rest)
				)
			)
			(V_MONEY
				(StartLesson)
			)
			(V_DAGGER
				(curRoom setScript: teacherTalk)
			)
			(V_SWORD
				(curRoom setScript: teacherTalk)
			)
			(V_ROCK
				(messager say: N_ROOM 59)
			)
			(V_FLAME
				(messager say: N_ROOM 59)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance masterTalker of Talker
	(properties
		x 10
		y 10
		view 1039
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2039)
		(PalVary PALVARYTARGET 2039)
		(AssertPalette 1039)
		(= font userFont)
		(super
			init: masterBust masterEyes masterTalkerMouth &rest
		)
	)
)

(instance masterBust of Prop
	(properties
		view 1039
	)
)

(instance masterTalkerMouth of Prop
	(properties
		nsTop 46
		nsLeft 54
		view 1039
		loop 1
	)
)

(instance masterEyes of Prop
	(properties
		nsTop 26
		nsLeft 57
		view 1039
		loop 2
	)
)
