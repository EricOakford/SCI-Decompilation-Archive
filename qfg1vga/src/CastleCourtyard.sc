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
	theUseSortedFeatures
	local1
	local2
	askedFight
	canFightMaster
	triedToSleep
	[local6 6] = [0 -42 -34 -32 41 999]
	[local12 6] = [0 40 39 16 35 999]
	[local18 6] = [0 40 38 16 31 999]
	[local24 6] = [0 29 28 33 36 999]
	[local30 6]
	[local36 5] = [0 -42 -34 -32 999]
)
(procedure (DeclineTraining)
	(messager say: N_ROOM 0 C_DECLINE)
)

(procedure (StartLesson)
	(if (not [egoStats PARRY])
		(messager say: N_ROOM 0 14)
	else
		(= askedFight NULL)
		(if (GiveMoney 10)
			(if (or (not (ego has: iSword)) (not (ego has: iShield)))
				(messager say: N_ROOM 0 C_LOANER)
			else
				(messager say: N_ROOM 0 C_PAYUP)
			)
			(= masterDay Day)
			(SolvePuzzle POINTS_FIGHTWEAPONMASTER 3 FIGHTER)
			((ScriptID 39 0) setScript: (ScriptID 222 2))
		else
			(messager say: N_ROOM 0 C_NO_MONEY 1 theMaster)
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
	
	(method (gotBeat param1)
		(self endFight: TRUE)
		(self setScript: param1)
	)
)

(instance swordMusic of Sound
	(properties)
)

(instance rm39 of Room
	(properties
		noun N_ROOM
		picture 39
		horizon 100
	)
	
	(method (init &tmp egoY temp1)
		(= [local30 0] @local6)
		(= [local30 1] @local12)
		(= [local30 2] @local18)
		(= [local30 3] @local24)
		(= [local30 4] 999)
		(Load RES_VIEW 39)
		(if
			(and
				(< TIME_DAWN timeODay)
				(< timeODay TIME_SUNSET)
				(not (Btst DEFEATED_WEAPON_MASTER))
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
		(= theUseSortedFeatures useSortedFeatures)
		(= useSortedFeatures FALSE)
		(SolvePuzzle POINTS_ENTERCASTLECOURTYARD 1)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 108 94 98 91 113 74 118 76 135 40 141 28 122 0 119
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 119 300 120 269 106 238 107 208 90 319 95
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 62 189 64 172 104 171 137 189
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
					((< egoX 100) (= egoX 100))
					((> egoX 215) (= egoX 215))
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
;;;			(castle init:)
;;;			(barrels init:)
;;;			(waggon init:)
;;;			(curbLeft init:)
;;;			(curbRite init:)
;;;			(wall init:)
;;;			(court init:)
		
		(if
			(and
				(< TIME_DAWN timeODay)
				(< timeODay TIME_SUNSET)
				(not (Btst DEFEATED_WEAPON_MASTER))
				(!= masterDay Day)
			)
			(cond 
				((Btst fFlag239)
					(masterTeller
						init: (ScriptID 39 2) @local6 @local30 @local36
					)
					((ScriptID 39 2) actions: masterTeller init:)
					(self setScript: (ScriptID 222 1))
				)
				((> (Random 0 100) 50)
					(masterTeller
						init: (ScriptID 39 2) @local6 @local30 @local36
					)
					((ScriptID 39 2)
						actions: masterTeller
						setCycle: Walk
						init:
					)
					(Bset fFlag239)
					(self setScript: (ScriptID 222 0))
				)
			)
			(if [egoStats PARRY] (= canFightMaster TRUE))
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
		(= useSortedFeatures theUseSortedFeatures)
		(if (>= Clock 3000)
			(Bclr fFlag239)
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
					(= triedToSleep TRUE)
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
		(if triedToSleep
			(= triedToSleep NULL)
			(if (and (< 750 Clock) (< Clock 2550))
				(FixTime 21)
			)
			(curRoom setScript: goTo37)
		else
			(SolvePuzzle POINTS_FIGHTWEAPONMASTER 3 FIGHTER)
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
		signal $0010
	)
	
	(method (doVerb theVerb)
		(rightGuard doVerb: theVerb)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego posn: 175 240 setMotion: MoveTo 160 180 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance egoExits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(switch (ego edgeHit?)
					(1
						(HandsOn)
						(curRoom newRoom: 41)
					)
					(3
						(ego setMotion: MoveTo (+ (ego x?) 15) 240 self)
					)
					(4
						(ego setMotion: PolyPath -20 (ego y?) self)
					)
					(2
						(ego setMotion: PolyPath 337 (ego y?) self)
					)
				)
			)
			(1
				(HandsOn)
				(switch (ego edgeHit?)
					(3
						(if Night
							(curRoom setScript: goTo37)
						else
							(curRoom newRoom: 37)
						)
					)
					(4 (curRoom newRoom: 38))
					(2 (curRoom newRoom: 40))
				)
			)
		)
	)
)

(instance comeBackLittleEgo of Script
	(properties)
	
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
					setMotion:
						MoveTo
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
				(= local1 1)
				(ego ignoreActors: 0)
				(client setScript: 0)
				(HandsOn)
			)
		)
	)
)

(instance goTo37 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if Night
					(if (< [egoStats CLIMB] 10)
						(messager say: N_ROOM 0 C_NO_CLIMB 1 self)
					else
						(messager say: N_ROOM 0 C_CLIMB_OVER 1 self)
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(if (not [egoStats PARRY])
					(messager say: N_ROOM 0 C_NO_PARRY)
				else
					(Bset fOfferedTraining)
					(messager say: N_ROOM 0 C_PRACTICE)
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
		(kernel_128 639)
		(super init:)
	)
	
	(method (doit)
		;added to address timer bug
		(if (< (Abs (- gameTime name)) 1) (return))
		(= name gameTime)
		(cond 
			((> yesNoTimer 1) (-- yesNoTimer))
			((and (== yesNoTimer 1) askedFight)
				(= yesNoTimer 0)
				(= askedFight NULL)
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
	(properties)
	
	(method (showDialog &tmp superShowDialog)
		(if
		(== (= superShowDialog (super showDialog:)) -34)
			(curRoom setScript: teacherTalk)
		)
		(return superShowDialog)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(if
				(not (< (ego distanceTo: (ScriptID 39 2)) 45))
					(rm39 setScript: comeBackLittleEgo)
				else
					(SolvePuzzle POINTS_TALKTOWEAPONMASTER 1)
					(super doVerb: theVerb &rest)
				)
			)
			(V_MONEY (StartLesson))
			(V_DAGGER
				(curRoom setScript: teacherTalk)
			)
			(V_SWORD
				(curRoom setScript: teacherTalk)
			)
			(V_ROCK (messager say: N_ROOM 59))
			(V_FLAME (messager say: N_ROOM 59))
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
		(kernel_128 1039)
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
