;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include game.sh) (include "22.shm")
(use Main)
(use Teller)
(use Procs)
(use Print)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm22 0
	skullTalker 1
)

(local
	local0
	local1
	hutX
	hutY
	gateOpen
	crushedByHut
	local6
	[local7 5] = [0 -7 -10 -8 999]
	[local12 3] = [0 11 999]
	[local15 3] = [0 12 999]
	[local18 4] = [0 9 13 999]
	[local22 6]
	[local28 5] = [0 -7 -10 -8 999]
)
(instance skullTimer of Timer)

(instance rm22 of Room
	(properties
		noun N_ROOM
		picture 22
		style HSHUTTER
	)
	
	(method (init)
		(= [local22 0] @local7)
		(= [local22 1] @local12)
		(= [local22 2] @local15)
		(= [local22 3] @local18)
		(= [local22 4] 999)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 0
						319 0
						319 189
						302 189
						302 182
						313 182
						309 152
						262 160
						200 160
						200 145
						183 145
						183 157
						176 156
						176 165
						67 163
						21 149
						18 185
						54 185
						54 189
					yourself:
				)
		)
		(LoadMany VIEW 22 23 19 531 516 1022)
		(LoadMany SOUND 23 28 66)
		(super init:)
		(cSound number: 23 loop: -1 play:)
		(cond 
			((Btst fGaveSkullGem)
				(= hutState hutGAVEGEM)
			)
			((not (Btst fBeenIn22))
				(= hutState 0)
			)
			((not (Btst fSkullOfferedDeal))
				(= hutState hutNODEAL)
			)
			((not (Btst 291))
				(= hutState hutNODEAL)
			)
			((not (Btst fGaveSkullGem))
				(= hutState 4)
			)
		)
		(if (not (Btst fBabaFlyAway))
			(if (not (== babaState babaFINALE))
				(= hutX 221)
				(= hutY 12)
				(feet init:)
			else
				(= hutX 221)
				(= hutY 109)
				(feet posn: 156 152 init:)
			)
			(hut posn: hutX hutY init:)
			(if (>= hutState hutGAVEGEM)
				(gateEyes init: setCycle: Forward)
			)
			(skullTeller init: gate @local7 @local22 @local28)
			(gate init: actions: skullTeller approachVerbs: 4 2 27)
		)
		(skull1 init: addToPic:)
		(skull2 init: addToPic:)
		(skull3 init: addToPic:)
		(skull4 init: addToPic:)
		(switch prevRoomNum
			(21
				(cond 
					((< babaState babaBRING) (curRoom setScript: frogIn))
					((== babaState babaFINALE) (LoadMany SOUND 28) (hut setScript: flyAway))
					(else (curRoom setScript: frogIn))
				)
			)
			(else 
				(self setScript: sEnterFromSouth)
			)
		)
		(skullEyes init: setScript: sRandomFlashingEyes)
		(roomF init:)
		(fence init:)
	)
	
	(method (doit)
		(cond 
			(
				(and
					(>= (ego y?) 187)
					(not (== (curRoom script?) sExitSouth))
					(not (== (curRoom script?) sEnterFromSouth))
				)
				(HandsOff)
				(curRoom setScript: sExitSouth)
			)
			(script)
			((and (< (ego y?) 148) (== (hut y?) 122))
				(HandsOff)
				(Bclr fBabaHutOpen)
				(Bclr fHutOnGround)
				(= local0 1)
				(ego setHeading: 360 self)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SLEEP (messager say: N_ROOM V_SLEEP 0))
			(V_DETECT (messager say: N_ROOM V_DETECT 0))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(if local0 (curRoom newRoom: 21))
		(if local1
			(= local1 0)
			(gate ignoreActors: 1 setScript: openGate)
		)
	)
	
	(method (newRoom newRoomNumber)
		(skullTimer dispose: delete:)
		(Bclr fMetSkull)
		(Bclr fBabaHutOpen) ;comment out to allow for the hutUp script to execute in Room 33.
		(Bclr fHutOnGround)
		(super newRoom: newRoomNumber)
	)
)

(instance roomF of Feature
	(properties
		x 159
		y 1
		z 100
		noun N_ROOM
		nsBottom 189
		nsRight 319
		sightAngle 40
	)
)

(instance fence of Feature
	(properties
		x 159
		y 23
		z 100
		noun N_FENCE
		nsTop 89
		nsBottom 158
		nsRight 319
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(switch (Random 1 3)
					(1 (messager say: N_FENCE V_LOOK 4))
					(2 (messager say: N_FENCE V_LOOK 6))
					(3 (messager say: N_FENCE V_LOOK 5))
				)
			)
			(V_DO
				(messager say: N_FENCE V_DO 0)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance skull1 of View
	(properties
		x 100
		y 100
		noun N_SKULL1
		view 23
		loop 4
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_SKULL1 V_LOOK 0)
		else
			(fence doVerb: theVerb &rest)
		)
	)
)

(instance skull2 of View
	(properties
		x 143
		y 101
		noun N_SKULL2
		view 23
		loop 5
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(= nightPalette 123)
		(PalVary PALVARYTARGET 123)
		(kernel_128 23)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_SKULL2 V_LOOK 0)
		else
			(fence doVerb: theVerb &rest)
		)
	)
)

(instance skull3 of View
	(properties
		x 239
		y 108
		noun N_SKULL3
		view 23
		loop 6
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_SKULL3 V_LOOK 0)
		else
			(fence doVerb: theVerb &rest)
		)
	)
)

(instance skull4 of View
	(properties
		x 266
		y 98
		noun N_SKULL4
		view 23
		loop 7
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(messager say: N_SKULL4 V_LOOK 0)
		else
			(fence doVerb: theVerb &rest)
		)
	)
)

(instance skullEyes of Prop
	(properties
		z -2
		view 23
		priority 11
		signal (| ignrAct fixPriOn)
	)
)

(instance dirt of Prop
	(properties
		x 191
		y 159
		view 23
		loop 1
		cel 3
		priority 4
		signal (| ignrAct fixPriOn)
		cycleSpeed 1
	)
)

(instance gateEyes of Prop
	(properties
		x 193
		y 121
		view 23
		loop 3
		priority 10
		signal $4810
		cycleSpeed 9
	)
)

(instance feet of Actor
	(properties
		x 156
		y 56
		noun N_FEET
		view 22
		loop 2
		priority 1
		signal $6810
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_FEET V_LOOK 0))
			(V_DO
				(cond 
					((Btst fBabaHutOpen) (messager say: N_FEET V_DO 3))
					(gateOpen (messager say: N_FEET V_DO 2))
					(else (messager say: N_FEET V_DO 1))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance wing1 of Actor
	(properties
		yStep 4
		view 22
		loop 8
		illegalBits $0000
		xStep 6
	)
)

(instance wing2 of Actor
	(properties
		yStep 4
		view 22
		loop 9
		illegalBits $0000
		xStep 6
	)
)

(instance hut of Actor
	(properties
		noun N_HUT
		yStep 4
		view 22
		priority 2
		signal $6010
		illegalBits $0000
		xStep 6
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(V_LOOK (messager say: N_HUT V_LOOK 0))
			(V_TALK
				(cond 
					((not gateOpen) (messager say: N_HUT V_ALTTALK 2))
					((Btst fBabaHutOpen) (messager say: N_HUT V_ALTTALK 3))
					((Btst fHutOnGround) (curRoom setScript: setAllTheWay))
					(else (curRoom setScript: sHutDown1))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance hutDoor of Actor
	(properties
		x 189
		y 83
		noun N_HUTDOOR
		view 22
		loop 5
		priority 3
		signal $4810
		cycleSpeed 1
		illegalBits $0000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (not cel)
					(messager say: N_HUTDOOR V_LOOK 17)
				else
					(messager say: N_HUTDOOR V_LOOK 18)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance gate of Actor
	(properties
		x 194
		y 158
		noun N_GATE
		approachX 191
		approachY 162
		yStep 1
		view 23
		priority 2
		signal $0810
		illegalBits $0000
		xStep 1
		moveSpeed 0
	)
)

(instance skullTeller of Teller
	(properties)
	
	(method (showDialog &tmp superShowDialog)
		(if
		(== (= superShowDialog (super showDialog:)) 12)
			(Bset fLearnedRhyme)
		)
		(if (and (== superShowDialog -8) (not (Btst fMadeDealWithSkull)))
			(gate setScript: makeTheDeal)
		)
		(return superShowDialog)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(cond 
					(gateOpen (messager say: N_GATE V_LOOK 2))
					((not gateOpen)
						(if (< hutState hutGAVEGEM)
							(messager say: N_GATE V_LOOK C_SKULL_EYELESS)
						else
							(messager say: N_GATE V_LOOK C_SKULL_HAS_EYES)
						)
					)
				)
			)
			(V_DO
				(if (== hutState hutGAVEGEM)
					(messager say: N_GATE V_DO C_SKULL_HAS_EYES)
					(gate setScript: openGate)
				else
					(messager say: N_GATE V_DO C_NO_ADMITTANCE)
				)
			)
			(V_MAGICGEM
				(SolvePuzzle f22GiveGem 10)
				(curRoom setScript: putEyes)
			)
			(V_TALK
				(if (== hutState hutGAVEGEM)
					(curRoom setScript: boneTalk)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(V_GREENFUR
				(messager say: N_GATE V_GREENFUR)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance TP of Actor
	(properties
		x 138
		y 176
		view 531
		signal $6000
	)
)

(instance tpSound of Sound
	(properties
		number 28
	)
)

(instance tromp of Sound
	(properties
		number 66
	)
)

(instance openGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theGame setCursor: waitCursor)
				(gateEyes dispose:)
				(dirt init: setCel: 0 setLoop: 1)
				(gate startUpd:)
				(ego startUpd:)
				(self cue:)
			)
			(1
				(dirt setCel: 1)
				(gate
					moveSpeed: 6
					ignoreActors: 1
					setMotion: MoveTo (gate x?) 210 self
				)
			)
			(2
				(dirt setCel: 2)
				(gate setMotion: MoveTo (gate x?) 220 self)
			)
			(3
				(dirt setCel: 3)
				(gate setMotion: MoveTo (gate x?) 229 self)
			)
			(4
				(= gateOpen TRUE)
				(dirt setCycle: 0 stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance flyAway of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= hutX (hut x?))
				(= hutY (hut y?))
				(wing1
					ignoreActors:
					ignoreHorizon:
					setPri: 15
					setLoop: 3
					posn: (+ hutX 3) (- hutY 22)
					init:
					moveSpeed: 6
					setStep: 1 1
					setCycle: Forward
				)
				(wing2
					ignoreActors:
					ignoreHorizon:
					setPri: 15
					setLoop: 4
					posn: (- hutX 72) (- hutY 20)
					init:
					moveSpeed: 6
					setStep: 1 1
					setCycle: Forward
				)
				(hutDoor dispose:)
				(hut setPri: (- (gate priority?) 1))
				(if (cast contains: gate) (gate addToPic:))
				(if (cast contains: dirt) (dirt addToPic:))
				(if (cast contains: gateEyes) (gateEyes addToPic:))
				(TP init: setCel: 255 setCycle: CycleTo 3 -1 self)
			)
			(1
				(ego posn: 138 176 init:)
				(TP setCycle: CycleTo 1 -1 self)
				(tpSound number: 28 init: play:)
				(Face ego hut)
			)
			(2 (TP dispose:) (= seconds 5))
			(3
				(feet
					setStep: 1 1
					setPri: (- (hut priority?) 1)
					moveSpeed: 6
					ignoreActors: 1
					ignoreControl: cWHITE
					setMotion: MoveTo (feet x?) -200
				)
				(= ticks 10)
			)
			(4
				(ego loop: 3 forceUpd:)
				(hut
					ignoreActors:
					ignoreHorizon:
					setLoop: 0
					moveSpeed: 6
					setStep: 1 1
					setMotion: MoveTo hutX -200
				)
				(wing1 setMotion: MoveTo (wing1 x?) -200)
				(wing2 setMotion: MoveTo (wing2 x?) -200)
				(= seconds 12)
			)
			(5
				(Bset fBabaFlyAway)
				(Bclr fBabaHutOpen)
				(messager say: N_ROOM NULL C_BABA_FLEES 1 self)
			)
			(6 (curRoom newRoom: 600))
		)
	)
)

(instance frogIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(TP init: setCel: 255 setPri: 15 setCycle: CycleTo 4 1 self)
			)
			(1
				(TP setCel: 4 setCycle: CycleTo 9 1 self)
				(if (>= babaState babaBRING)
					(ego view: 0 loop: 6 posn: 138 176 ignoreActors: 1 init:)
					(NormalEgo)
				else
					(ego
						view: 531
						loop: 1
						setCel: 255
						posn: 138 176
						ignoreActors: 1
						init:
					)
				)
				(tpSound number: 28 init: play:)
			)
			(2
				(TP dispose:)
				(if (>= babaState babaBRING)
					(ego
						view: 0
						loop: 6
						posn: 138 176
						actions: egoActions
						ignoreActors: 1
						init:
					)
					(NormalEgo)
					(self changeState: 6)
				else
					(messager say: N_ROOM 0 C_FROG_IN 0 self)
				)
			)
			(3
				(tpSound play:)
				(ego setCycle: BegLoop self)
				(= cycles 2)
			)
			(4 (= ticks 6))
			(5
				(NormalEgo)
				(ego loop: 3)
				(= cycles 10)
			)
			(6
				(HandsOn)
				(switch babaState
					(babaFETCH
						(messager say: N_ROOM 0 C_CURSED)
						(Bset fBabaCurse)
						(= dayCursedByBabaYaga Day)
					)
					(babaBRING (messager say: N_ROOM 0 C_AFTER_ROOT))
				)
				(self dispose:)
			)
		)
	)
)

(instance sRandomFlashingEyes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch (Random 1 4)
					(1
						(skullEyes
							setLoop: 4
							setCel: 1
							posn: (skull1 x?) (- (skull1 y?) 1)
						)
					)
					(2
						(skullEyes
							setLoop: 5
							setCel: 1
							posn: (skull2 x?) (- (skull2 y?) 1)
						)
					)
					(3
						(skullEyes
							setLoop: 6
							setCel: 1
							posn: (skull3 x?) (- (skull3 y?) 1)
						)
					)
					(4
						(skullEyes
							setLoop: 7
							setCel: 1
							posn: (skull4 x?) (- (skull4 y?) 1)
						)
					)
				)
				(= ticks (Random 20 80))
			)
			(1 (self init:))
		)
	)
)

(instance sExitSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (ego x?) 245 self)
			)
			(1 (curRoom newRoom: 33))
		)
	)
)

(instance sEnterFromSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(NormalEgo)
				(theGame setCursor: waitCursor)
				(ego
					init:
					actions: egoActions
					posn: 152 245
					setMotion: MoveTo 152 180 self
				)
			)
			(1 (= cycles 2))
			(2
				(if (not (Btst fBeenIn22))
					(Bset fBeenIn21)
					(messager say: N_ROOM 0 0 3 self)
				else
					(= ticks 30)
				)
			)
			(3
				(HandsOn)
				(NormalEgo)
				(Load VIEW 1022)
				(gate setScript: boneTalk)
				(self dispose:)
			)
		)
	)
)

(instance sHutDown1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theGame setCursor: waitCursor)
				(hut posn: 221 24)
				(feet posn: 156 68)
				(= cycles 1)
			)
			(1
				(hut posn: 221 34)
				(feet posn: 156 78)
				(= cycles 1)
			)
			(2
				(hut posn: 221 45)
				(feet posn: 156 89)
				(= cycles 1)
			)
			(3
				(hut setLoop: 1 posn: 193 37)
				(feet hide:)
				(= cycles 5)
			)
			(4
				(hut setLoop: 0 posn: 221 61)
				(feet show: posn: 156 105)
				(= cycles 1)
			)
			(5
				(hut posn: 221 77)
				(feet posn: 156 121)
				(= cycles 1)
			)
			(6
				(hut posn: 221 86)
				(feet posn: 156 130)
				(= cycles 1)
			)
			(7
				(hut posn: 221 98)
				(feet posn: 156 142)
				(tromp number: 66 init: play:)
				(ShakeScreen 3)
				(HandsOn)
				(Bset fHutOnGround)
				(self dispose:)
			)
		)
	)
)

(instance sHutDown2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theGame setCursor: waitCursor)
				(SolvePuzzle f22HutSit 7)
				(if (ego inRect: 156 115 238 151)
					(ego view: 516 setLoop: 1 setCel: 0 setPri: 1)
					(= crushedByHut TRUE)
				)
				(= cycles 5)
			)
			(1
				(if crushedByHut (ego setCel: 3))
				(hut posn: 221 109)
				(feet posn: 156 152)
				(= cycles 3)
			)
			(2
				(if crushedByHut (ego setCel: 5))
				(hut posn: 221 116)
				(= cycles 3)
			)
			(3
				(hut posn: 221 122 stopUpd:)
				(tromp number: 66 init: play:)
				(ShakeScreen 3)
				(= cycles 3)
			)
			(4
				(hutDoor init: cycleSpeed: 6 setCycle: EndLoop self)
			)
			(5
				(hutDoor stopUpd:)
				(if crushedByHut (EgoDead 157 158 3 7 516))
				(HandsOn)
				(Bset fBabaHutOpen)
				(self dispose:)
			)
		)
	)
)

(instance putEyes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego use: iMagicGem)
				(= hutState hutGAVEGEM)
				(HandsOff)
				(if (and (== (ego x?) 191) (== (ego y?) 158))
					(self cue:)
				else
					(ego setMotion: PolyPath 191 162 self)
				)
			)
			(1
				(ego loop: 3 forceUpd:)
				(= cycles 2)
			)
			(2
				(messager say: N_ROOM 0 0 4 self)
				(gateEyes init: setCycle: Forward)
				(= hutState hutGAVEGEM)
			)
			(3
				(ego setMotion: MoveTo 200 175 self)
			)
			(4
				(NormalEgo)
				(theGame setCursor: waitCursor)
				(ego loop: 3 forceUpd:)
				(= cycles 2)
			)
			(5
				(Bset fGaveSkullGem)
				(ego stopUpd:)
				(gate stopUpd:)
				(= cycles 2)
			)
			(6 (messager say: N_ROOM 0 0 5 self))
			(7
				(if modelessDialog (modelessDialog dispose:))
				(= ticks 10)
			)
			(8 (messager say: N_ROOM 0 0 6 self))
			(9 (= ticks 10))
			(10
				(messager say: N_ROOM 0 0 7 self)
			)
			(11
				(if (not (Btst fBeenIn21))
					(messager say: N_ROOM 0 28 1 self)
				else
					(messager say: N_ROOM 0 33 1 self)
				)
			)
			(12
				(if modelessDialog (modelessDialog dispose:))
				(ego startUpd:)
				(gate startUpd:)
				(= local1 1)
				(skullTimer setReal: curRoom 2)
				(self dispose:)
			)
		)
	)
)

(instance openingScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				(or (== hutState hutMETSKULL) (== hutState hutNODEAL))
					(self cue:)
				else
					(= hutState hutMETSKULL)
					(messager say: N_ROOM 0 34 1 self)
				)
			)
			(1 (= seconds 10))
			(2
				(if (== hutState hutNODEAL)
					(self cue:)
				else
					(= hutState hutNODEAL)
					(messager say: N_ROOM 0 31 1 self)
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance boneTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0 setHeading: 360 self)
			)
			(1
				(gate stopUpd:)
				(ego stopUpd:)
				(= ticks 60)
			)
			(2
				(switch hutState
					(0
						(= hutState hutMETSKULL)
						(ego setScript: doTheIntro)
					)
					(2
						(if (Btst fSkullOfferedDeal)
							(gate setScript: makeTheDeal)
						else
							(gate setScript: hearTheDeal)
						)
					)
					(3
						(messager say: N_ROOM 0 30 1 self)
						(= hutState hutNODEAL)
						(gate setScript: makeTheDeal)
					)
					(4
						(gate setScript: respondToQuestion)
					)
					(else 
						(if (and (Btst fBeenIn21) (ego has: iMandrake))
							(messager say: N_ROOM 0 21)
							(gate setScript: openGate)
						else
							(messager say: N_ROOM 0 27)
							(gate setScript: openGate)
						)
					)
				)
			)
			(3
				(if (== hutState hutNODEAL)
					(self changeState: 2)
				else
					(if
						(and
							(> hutState 4)
							(> register 0)
							(< register 3)
						)
						(= state (- state 2))
					)
					(= seconds 2)
				)
			)
			(4
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(gate startUpd:)
				(ego startUpd:)
				(NormalEgo)
				(HandsOn)
				(if (and (ego has: iMandrake) (Btst fGaveSkullGem))
					(gate setScript: openGate)
				)
				(self dispose:)
			)
		)
	)
)

(instance setAllTheWay of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 3))
			(1
				(messager say: N_HUT V_ALTTALK C_ASK_RHYME 1 self)
				(theGame setCursor: ARROW_CURSOR TRUE 140 80)
			)
			(2
				(if (Btst fLearnedRhyme)
					(switch
						(Print
							addText: 6 0 23 1 0 0 22
							addButton: 0 6 0 23 9 0 18 22
							addButton: 1 6 0 23 2 0 36 22
							addButton: 2 6 0 23 3 0 54 22
							addButton: 3 6 0 23 4 0 72 22
							init:
						)
						(0 (HandsOn) (self dispose:))
						(1 (messager say: N_ROOM 0 4 1 self))
						(2 (messager say: N_ROOM 0 6 1 self))
						(3
							(messager say: N_ROOM 0 5 1 self)
							(curRoom setScript: sHutDown2)
						)
					)
				else
					(switch
						(Print
							addText: 6 0 23 5 0 0 22
							addButton: 0 6 0 23 9 0 18 22
							addButton: 1 6 0 23 6 0 36 22
							addButton: 2 6 0 23 7 0 54 22
							init:
						)
						(0 (HandsOn) (self dispose:))
						(1 (messager say: N_ROOM 0 4 0 self))
						(2 (messager say: N_ROOM 0 6 0 self))
					)
				)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance doTheIntro of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM 0 34 1 self)
			)
			(1
				(= hutState hutNODEAL)
				(messager say: N_ROOM 0 31 1 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance hearTheDeal of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM 0 35 1 self)
			)
			(1
				(messager say: N_ROOM 0 37 1 self)
			)
			(2
				(self setScript: makeTheDeal)
			)
		)
	)
)

(instance makeTheDeal of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Btst 292)
				(Bset fSkullOfferedDeal)
				(if modelessDialog (modelessDialog dispose:))
				(= seconds 3)
			)
			(1
				(switch
					(Print
						addText: 6 0 23 8 0 0 22
						addButton: 0 6 0 23 9 0 18 22
						addButton: 1 6 0 23 10 0 36 22
						addButton: 2 6 0 23 11 0 54 22
						init:
					)
					(0
						(messager say: N_ROOM 0 26 1)
						(= hutState 3)
					)
					(1
						(Bset fMadeDealWithSkull)
						(= hutState 4)
						(messager say: N_ROOM 0 24 1)
						(SolvePuzzle f22MakeTheDeal 2)
					)
					(2
						(messager say: N_ROOM 0 25 1)
						(= hutState 3)
					)
				)
				(= seconds 3)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance respondToQuestion of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Btst 292)
				(Bset fSkullOfferedDeal)
				(if modelessDialog (modelessDialog dispose:))
				(= seconds 3)
			)
			(1
				(switch
					(Print
						addText: 6 0 32 1 0 0 22
						addButton: 0 6 0 23 9 0 36 22
						addButton: 1 6 0 23 10 0 54 22
						addButton: 2 6 0 23 11 0 72 22
						init:
					)
					(0
						(messager say: N_ROOM 0 26 1)
						(= hutState 3)
					)
					(1
						(if (ego has: iMagicGem)
							(SolvePuzzle f22GiveGem 10)
							(curRoom setScript: putEyes)
						else
							(messager say: N_ROOM 0 26 1)
						)
					)
					(2
						(messager say: N_ROOM 0 25 1)
						(= hutState 3)
					)
				)
				(= seconds 3)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance skullTalker of Talker
	(properties
		x 10
		y 10
		view 1022
		talkWidth 260
		textX 15
		textY 110
		blinkSpeed 10
	)
	
	(method (init)
		(= nightPalette 2022)
		(PalVary PALVARYTARGET 2022)
		(kernel_128 1022)
		(= font userFont)
		(if (Btst fGaveSkullGem)
			(super init: skullBust bigEyes skullTalkerMouth &rest)
		else
			(super init: skullBust 0 skullTalkerMouth &rest)
		)
	)
)

(instance skullBust of Prop
	(properties
		view 1022
	)
)

(instance skullTalkerMouth of Prop
	(properties
		nsTop 58
		nsLeft 35
		view 1022
		loop 1
	)
)

(instance bigEyes of Prop
	(properties
		nsTop 39
		nsLeft 38
		view 1022
		loop 2
		cycleSpeed 12
	)
)

(instance egoActions of Actions
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb V_MUSHROOM)
				(messager say: N_FENCE V_MUSHROOM)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance sBlink of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: EndLoop self))
			(1 (= ticks (Random 30 90)))
			(2 (client setCycle: BegLoop self))
			(3 (= ticks (Random 30 90)))
			(4 (self init:))
		)
	)
)
