;;; Sierra Script 1.0 - (do not remove this comment)
(script# 96)
(include game.sh) (include "96.shm")
(use Main)
(use Teller)
(use Procs)
(use Talker)
(use MoveCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use DPath)
(use Reverse)
(use Wander)
(use Chase)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm96 0
	finalExit 1
	doorFall 2
	rollOut 3
	yorickTalker 4
	yorick 5
	chain 6
)

(local
	local0
	angYorickToEgo
	local2
	initThrowTimer
	throwTimer
	chainDoorTimer
	chainDoorIsOpen
	yorickLeaveCued
	yorickGone
	local9
	[fly 7]
	flyX = [160 164 160 156 155 155 157]
	flyY = [14 15 9 12 13 10 8]
	throwCued
	yorickX = [-13 -7 11]
	yorickY = [-1 -10 -11]
	thingY = [8 9 9]
	yorickX2 = [-11 -8 -3 4 7]
	yorickY2 = [-26 -26 -26 -26 -26]
	thingY2 = [-1 1 2 3 1]
	yorickLeavePath = [
		1 0
		161 69
		1 1
		161 66
		1 2
		160 62
		1 3
		159 59
		1 4
		159 59
		PATHEND
		]
	shakeCount
	local78
	saveSpeed
	yorickTellMainBranch = [
		STARTTELL
		-11		;C_YORICK
		-27		;C_LEADER
		-24		;C_EXIT
		-30		;C_MINOTAUR
		ENDTELL
		STARTTELL
		C_WARLOCK
		C_CARAVAN
		C_POWDER
		C_JESTER
		C_ME
		C_POTION
		ENDTELL
		]
	yorickTell1 = [
		STARTTELL
		-23		;C_ELSA
		ENDTELL
		]
	yorickTell2 = [
		STARTTELL
		C_MAZE
		ENDTELL
		]
	yorickTell3 = [
		STARTTELL
		C_FORTRESS
		ENDTELL
		]
	yorickTell4 = [
		STARTTELL
		C_DISENCHANTMENT
		C_DISPEL
		C_SWORDY_LORDY
		C_MIRROR
		C_BARON
		ENDTELL
		]
	[yorickTellTree 11]
	[yorickTellKeys 7] = [
		STARTTELL
		-11		;C_YORICK
		-27		;C_LEADER
		-24		;C_EXIT
		-30		;C_MINOTAUR
		-23		;C_ELSA
		ENDTELL
		]
)
(procedure (Disoriented)
	(switch (++ yesNoTimer)
		(1 (messager say: N_ROOM NULL C_DISORIENTED 1))
		(2 (messager say: N_ROOM NULL C_DISORIENTED 2))
		(3 (messager say: N_ROOM NULL C_DISORIENTED 3))
		(4 (messager say: N_ROOM NULL C_DISORIENTED 4))
		(5 (messager say: N_ROOM NULL C_DISORIENTED 5))
		(6 (messager say: N_ROOM NULL C_DISORIENTED 6))
		(7 (messager say: N_ROOM NULL C_DISORIENTED 7))
	)
)

(procedure (InitFlies &tmp i)
	(for ((= i 0)) (< i 6) ((++ i))
		(= [fly i] (Clone (ScriptID 98 28)))
		([fly i]
			ignoreActors:
			posn: [flyX i] [flyY i]
			init:
			setLoop: 9
			setPri: 15
		)
		(if (< howFast fastest)
			([fly i] addToPic:)
		else
			([fly i] setCycle: Forward setMotion: Wander)
		)
	)
)

(instance isScript of Code
	(method (doit obj)
		(if (obj respondsTo: #script)
			(obj script?)
		)
	)
)

(instance rm96 of Room
	(properties
		noun N_ROOM
		picture 96
		style WIPELEFT
	)
	
	(method (init)
		(|= disabledActions (| ACTION_RUN ACTION_SNEAK))
		(narrator y: 21)
		(= [yorickTellTree 0] @yorickTellMainBranch)
		(= [yorickTellTree 1] @yorickTell1)
		(= [yorickTellTree 2] @yorickTell2)
		(= [yorickTellTree 3] @yorickTell3)
		(= [yorickTellTree 4] @yorickTell4)
		(= [yorickTellTree 5] @yorickTellMainBranch)
		(= [yorickTellTree 6] ENDTELL)
		(LoadMany RES_SCRIPT REVERSE JUMP CHASE WANDER)
		MoveCycle
		DPath
		(cSound stop:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						275 122
						283 114
						298 116
						303 129
						268 129
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						18 124
						23 116
						45 115
						41 124
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						4 113
						4 146
						0 146
						0 113
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						319 113
						315 113
						315 108
						319 108
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 82
						0 64
						19 64
						20 83
						0 83
					yourself:
				)
		)
		(super init: &rest)
		(SolvePuzzle f96EnterJesterRoom 8)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(= throwTimer (= initThrowTimer 300))
		(roomMusic init: play:)
		(InitFlies)
		(= yesNoTimer 0)
		(yorickTeller init: yorick @yorickTellMainBranch @yorickTellTree @yorickTellKeys)
		(yorick init: setPri: 3 actions: yorickTeller stopUpd:)
		(features
			add:
				(ScriptID 98 1)
				(ScriptID 98 2)
				(ScriptID 98 3)
				(ScriptID 98 5)
				(ScriptID 98 31)
				littleSign
				(ScriptID 98 33)
				(ScriptID 98 34)
				(ScriptID 98 35)
			eachElementDo: #init
			doit:
		)
		;UPGRADE
;;;		((ScriptID 98 1) init:)
;;;		((ScriptID 98 2) init:)
;;;		((ScriptID 98 3) init:)
;;;		((ScriptID 98 5) init:)
;;;		((ScriptID 98 31) init:)
;;;		(littleSign init:)
;;;		((ScriptID 98 33) init:)
;;;		((ScriptID 98 34) init:)
;;;		((ScriptID 98 35) init:)
		
		(if (< howFast fastest)
			((ScriptID 98 25) init: setPri: 14 addToPic:)
		else
			((ScriptID 98 25)
				init:
				setPri: 14
				cycleSpeed: 6
				setCycle: Forward
			)
		)
		((ScriptID 98 32) setPri: 7 init:)
		((ScriptID 98 13) ignoreActors: init:)
		((ScriptID 98 14)
			ignoreActors:
			setPri: 11
			init:
			stopUpd:
		)
		((ScriptID 98 15) ignoreActors: init: setPri: 8)
		(trap4 ignoreActors: setPri: 5 init:)
		(chain ignoreActors: init:)
		(door11 ignoreActors: cycleSpeed: 6 init:)
		((ScriptID 98 20) ignoreActors: cycleSpeed: 6 init:)
		(door3 ignoreActors: cycleSpeed: 6 init:)
		((ScriptID 98 16)
			ignoreActors:
			cycleSpeed: 6
			setPri: 11
			init:
			stopUpd:
		)
		((ScriptID 98 24) ignoreActors: cycleSpeed: 6 init:)
		(door13 ignoreActors: cycleSpeed: 6 init: stopUpd:)
		((ScriptID 98 21) ignoreActors: cycleSpeed: 6 init:)
		((ScriptID 98 30)
			init:
			posn: 500 500
			setLoop: 8
			stopUpd:
		)
		(ChangeGait MOVE_WALK FALSE)
		(NormalEgo)
		(ego posn: 89 186 init: setScript: talksAlot)
	)
	
	(method (doit)
		;added to fix speed bug
		(if (< (Abs (- gameTime name)) 2) (return))
		(= name gameTime)
		(cond 
			(
				(and
					(!= (ego onControl: origin) cBLACK)
					(not (Btst fOpeningDoor))
					(not (Btst fRollingOut))
					(not (Btst fYorickThrows))
				)
				(Bset fOpeningDoor)
				(switch (ego onControl: origin)
					(cLBLUE
						(Bset fFallTrapdoor)
						(if chainDoorIsOpen
							(= chainDoorTimer 1)
						)
						(ego setPri: 15)
						((ScriptID 98 14) setScript: trapFall)
					)
					(cBROWN
						(Bset fFallTrapdoor)
						(if chainDoorIsOpen
							(= chainDoorTimer 1)
						)
						(ego setPri: (+ (ego priority?) 1))
						((ScriptID 98 15) setScript: trapFall)
					)
					(cLGREY
						(Bset fFallTrapdoor)
						(if chainDoorIsOpen
							(= chainDoorTimer 1)
						)
						((ScriptID 98 15) setScript: trapFall)
					)
					(cLCYAN
						(Bset fFallTrapdoor)
						(if chainDoorIsOpen
							(= chainDoorTimer 1)
						)
						(trap4 setScript: trapFall)
					)
					(cGREEN
						(if chainDoorIsOpen
							(= chainDoorTimer 1)
						)
						(if (ego inRect: 15 97 61 106)
							(Bset fFallTrapdoor)
							((ScriptID 98 13) setScript: trapFall 0 0)
						else
							(ego setScript: fallDownscreen)
						)
					)
					(cBLUE
						(if chainDoorIsOpen
							(= chainDoorTimer 1)
						)
						(if (ego inRect: 42 91 90 105)
							(Bset fFallTrapdoor)
							((ScriptID 98 13) setScript: trapFall 0 1)
						else
							(ego setScript: fallUpscreen)
						)
					)
					(cRED
						(Bset fFallingOffLedge)
						(if chainDoorIsOpen
							(= chainDoorTimer 1)
						)
						(if (ego inRect: 66 95 84 105)
							(Bset fFallTrapdoor)
							((ScriptID 98 13) setScript: trapFall 0 3)
						else
							(ego setScript: fallSideways)
						)
					)
					(cCYAN
						(if chainDoorIsOpen
							(= chainDoorTimer 1)
						)
						(cond 
							((and (not (Btst fFakeDoorDown)) (< (ego y?) 86))
								(ego setScript: fallDownscreen)
							)
							((> (ego y?) 135)
								(ego setScript: fallSideways 0 1)
							)
							((> (ego y?) 107)
								(ego setScript: fallSideways)
							)
							((> (ego y?) 86)
								(ego setScript: fallDownscreen)
							)
							(else
								(Bclr fOpeningDoor)
							)
						)
					)
					(cMAGENTA
						(if (not (Btst fFakeDoorDown))
							(Bset fFallTrapdoor)
							(if chainDoorIsOpen
								(= chainDoorTimer 1)
							)
							((ScriptID 98 13) setScript: trapFall 0 2)
						else
							(Bclr fOpeningDoor)
						)
					)
					(else
						(Bclr fOpeningDoor)
					)
				)
			)
			((ego inRect: 269 130 302 146)
				(ego priority: 15)
			)
		)
		(if (and (not (Btst fGoThruDoorway)) (not (Btst fOpeningDoor)))
			(cond 
				((ego inRect: 309 100 319 112)
					(if (not (Btst fRollingOut))
						(Bset fGoThruDoorway)
						(ego setScript: goTo2)
					)
				)
				((and (== (ego edgeHit?) WEST) (< (ego y?) 150))
					(Bset fGoThruDoorway)
					(if chainDoorIsOpen
						(= chainDoorTimer 1)
					)
					(ego setScript: goTo12)
				)
				((and (ego inRect: 11 45 33 56) (not (ego script?)))
					(Bset fGoThruDoorway)
					(ego setScript: goTo15)
				)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(= disabledActions 0)
		(narrator y: 14)
		(= nightPalette 0)
		(Bset fBeenIn96)
		(= yesNoTimer 0)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
		(DisposeScript DPATH)
		(DisposeScript MOVECYC)
		(DisposeScript 98)
	)
	
	(method (handleEvent event)
		(if (== (ego script?) rollOut)
			(event claimed: TRUE)
			((ScriptID 98 31) handleEvent: event)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
				(if (and (IsObject yorick) (not yorickGone))
					(messager say: N_ROOM V_FLAME C_YORICK 1 self)
					(return TRUE)
				else
					(messager say: N_ROOM V_FLAME C_TOOFARAWAY)
					(return TRUE)
				)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(= yorickGone 1)
		((ScriptID 98 21) setScript: takeADive)
	)
)

(instance littleSign of Feature
	(properties
		noun N_LITTLESIGN
		nsTop 35
		nsLeft 243
		nsBottom 48
		nsRight 258
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
			(curRoom doVerb: theVerb &rest)
			(return TRUE)
		)
		(return
			(switch theVerb
				(V_LOOK
					(messager say: N_LITTLESIGN V_LOOK)
				)
				(V_DO
					(if (and (== (ego onControl: origin) cGREY) (> (ego x?) 100))
						(door13 setScript: happyFace)
					else
						(messager say: N_LITTLESIGN V_DO C_TOOFARAWAY)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance chain of View
	(properties
		x 65
		y 1
		noun N_CHAIN
		view 290
		loop 7
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
			(curRoom doVerb: theVerb &rest)
			(return TRUE)
		)
		(return
			(switch theVerb
				(V_DO
					(if (== (ego onControl: origin) cLMAGENTA)
						(= chainDoorIsOpen TRUE)
						(= chainDoorTimer 1000)
						(door11 setScript: (ScriptID 98 36))
					else
						(messager say: N_CHAIN V_DO)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance trap4 of Prop
	(properties
		x 159
		y 76
		noun N_TRAP4
		view 296
		loop 3
	)
	
	(method (doit)
		;added to fix speed bug
		(if (< (Abs (- gameTime name)) 2) (return))
		(= name gameTime)
		(if (and (Btst fFallTrap4) (not script))
			(Bclr fFallTrap4)
			(self setScript: fallDownscreen)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(return
			(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
				(curRoom doVerb: theVerb &rest)
				(return TRUE)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance door11 of Prop
	(properties
		x 214
		y 66
		noun N_DOOR11
		view 96
	)
	
	(method (doit)
		;added to fix speed bug
		(if (< (Abs (- gameTime name)) 2) (return))
		(= name gameTime)
		(cond 
			((> chainDoorTimer 1)
				(if (== (ego onControl: origin) cLGREEN)
					(= chainDoorTimer 0)
					(= chainDoorIsOpen 0)
					(self setScript: goTo6)
				else
					(-- chainDoorTimer)
				)
			)
			((== chainDoorTimer 1)
				(= chainDoorTimer 0)
				(= chainDoorIsOpen 0)
				(self setCycle: BegLoop)
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
			(curRoom doVerb: theVerb &rest)
			(return TRUE)
		)
		(return
			(switch theVerb
				(V_DO
					(if (== (ego onControl: origin) cLGREEN)
						(door11 setScript: brickWall)
					else
						(messager say: N_DOOR11 V_DO C_TOOFARAWAY)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance door3 of Prop
	(properties
		x 59
		y 86
		noun N_DOOR3
		view 290
		loop 4
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
			(curRoom doVerb: theVerb &rest)
			(return TRUE)
		)
		(return
			(switch theVerb
				(V_DO
					(if (ego inRect: 49 86 83 91)
						(Bset fOpeningDoor)
						(door3 setScript: knockOut)
					else
						(messager say: N_DOOR3 V_DO C_TOOFARAWAY)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance door13 of Prop
	(properties
		x 260
		y 5
		noun N_DOOR13
		view 290
		loop 5
	)
	
	(method (doit)
		;added to fix speed bug
		(if (< (Abs (- gameTime name)) 2) (return))
		(= name gameTime)
		(if (Btst fBallConks)
			(Bclr fBallConks)
			(Bset fOpeningDoor)
			(ego
				setPri: (+ (ego priority?) 1)
				setScript: fallSideways
			)
		)
		(super doit:)
	)
)

(instance yorick of Prop
	(properties
		x 161
		y 69
		noun N_YORICK
		view 297
		loop 1
	)
	
	(method (init)
		(= nightPalette 196)
		(PalVary PALVARYTARGET 196)
		(AssertPalette 96)
		(super init:)
	)
	
	(method (doit)
		;added to fix speed bug
		(if (< (Abs (- gameTime name)) 2) (return))
		(= name gameTime)
		(cond 
			((> throwTimer 1)
				(-- throwTimer)
			)
			((== throwTimer 1)
				(= throwTimer initThrowTimer)
				(if
					(and
						(Btst fNoMoreTalking)
						(not (cast firstTrue: #perform isScript))
						(not (Btst fGoThruDoorway))
						(not isHandsOff)
						(< (ego x?) 295)
						(!= (ego onControl: origin) cYELLOW)
						(!= (ego onControl: origin) cLMAGENTA)
						(not local2)
						(> (ego y?) 83)
					)
					(Bset fYorickThrewSomething)
					(self setScript: throwIt)
				)
			)
		)
		(if (Btst fPulledChain)
			(cond 
				(
					(<
						(= angYorickToEgo
							(GetAngle (yorick x?) (yorick y?) (ego x?) (ego y?))
						)
						145
					)
					(if (!= (yorick cel?) 2)
						(yorick setCel: 2)
					)
				)
				((< angYorickToEgo 215)
					(if (!= (yorick cel?) 1)
						(yorick setCel: 1)
					)
				)
				((!= (yorick cel?) 0)
					(yorick setCel: 0)
				)
			)
		)
		(cond 
			((and (Btst fYorickLikesYou) (not yorickLeaveCued) (not yorickGone))
				(= yorickLeaveCued TRUE)
				((ScriptID 98 32) dispose:)
				(self view: 298 setLoop: 0 setCel: 0)
			)
			((and yorickLeaveCued (ego mover?))
				(NormalEgo)
				(= yorickLeaveCued 0)
				(= yorickGone TRUE)
				((ScriptID 98 21) setScript: takeADive)
			)
		)
		(super doit:)
	)
)

(instance yorickTeller of Teller
	(method (showDialog &tmp superShowDialog)
		(if (== (= superShowDialog (super showDialog:)) -23)
			(SolvePuzzle f96AskAboutElsa 8)
			;UPGRADE: Flag now set to allow Yorick to leave
			(Bset fYorickLikesYou)
		)
		(return superShowDialog)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
			(curRoom doVerb: theVerb &rest)
			(return TRUE)
		)
		(return
			(switch theVerb
				(V_TALK
					(SolvePuzzle f96TalkToJester 2)
					(super doVerb: theVerb &rest)
				)
				(V_DO
					(messager say: N_YORICK V_DO)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance fallUpscreen of Script	
	(method (dispose)
		(Bset fNoMoreTalking)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not (Btst fPulledChain))
					((ScriptID 98 32) setCel: 3)
					(yorick setLoop: 3 cel: 0 cycleSpeed: 8 setCycle: Forward)
				)
				(ego view: 537 setLoop: 0 cel: 0 setCycle: CycleTo 2 1 self)
				(if (and register (> (ego x?) 75))
					(ego x: (- (ego x?) 20))
				)
			)
			(1
				(Palette PALIntensity 71 255 0)
				(ego
					setPri:
						(cond 
							((< (ego y?) 107) 6)
							((< (ego y?) 135) 10)
							(else 12)
						)
				)
				(ego
					yStep: 15
					illegalBits: 0
					moveSpeed: 0
					setMotion: MoveTo (ego x?) 250 self
				)
			)
			(2
				(Palette PALIntensity 71 255 100)
				(if (not (TakeDamage 5))
					(EgoDead 114 115 2 5 517)
				else
					(Bclr fFallingOffLedge)
					(Bclr fOpeningDoor)
					(Bset fRollingOut)
					(if (Btst fFallTrapdoor)
						(Bclr fFallTrapdoor)
						(ego setScript: rollOut)
						(if (or (== client trap4) (== client (ScriptID 98 13)))
							(client setPri: (+ (client priority?) 1))
						)
						(client setCel: 0 setScript: 0)
					else
						(ego setScript: rollOut)
					)
				)
			)
		)
	)
)

(instance fallDownscreen of Script
	(method (dispose)
		(Bset fNoMoreTalking)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not (Btst fFakeDoorDown))
					((ScriptID 98 20) setPri: 1)
				)
				(if (and (< (ego y?) 110) (!= client trap4))
					(trap4 setPri: 6)
					(ego setPri: (- (ego priority?) 1))
				)
				(if (not (Btst fPulledChain))
					((ScriptID 98 32) setCel: 3)
					(yorick setLoop: 3 cel: 0 cycleSpeed: 8 setCycle: Forward)
				)
				(ego view: 536 setLoop: 1 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(1
				(Palette PALIntensity 71 255 0)
				(ego
					setPri:
						(cond 
							((< (ego y?) 82) 3)
							((< (ego y?) 107) 6)
							((< (ego y?) 135) 10)
							(else 12)
						)
				)
				(if (< (ego y?) 110)
					(ego setPri: (- (ego priority?) 2))
				)
				(ego
					yStep: 12
					illegalBits: 0
					setCycle: EndLoop
					setMotion: MoveTo (ego x?) 220 self
				)
			)
			(2
				(Palette PALIntensity 71 255 100)
				((ScriptID 98 20) setPri: -1)
				(if (not (TakeDamage 5))
					(EgoDead 114 115 2 5 517)
				else
					(Bclr fFallingOffLedge)
					(Bclr fOpeningDoor)
					(Bset fRollingOut)
					(if (Btst fFallTrapdoor)
						(Bclr fFallTrapdoor)
						(ego setScript: rollOut)
						(client
							setCel: 0
							setPri: (+ (client priority?) 1)
							setScript: 0
						)
					else
						(trap4 setPri: 5)
						(ego setPri: (+ (ego priority?) 2))
						(ego setScript: rollOut)
					)
				)
			)
		)
	)
)

(instance rollOut of Script
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theGame setCursor: 942 TRUE)
				(User canInput: TRUE)
				(= saveSpeed speed)
				(if (Btst fFakeDoorDown)
					(Bclr fFakeDoorDown)
					((ScriptID 98 20) setCycle: BegLoop)
				)
				(if (not (Btst fPulledChain))
					(yorick setLoop: 3 setCycle: Forward)
				)
				(if (trap4 cel?)
					(trap4 setCycle: BegLoop)
				)
				(door11 setCycle: EndLoop self)
			)
			(1
				(ego edgeHit: 0)
				(ego
					view: 296
					setLoop: 4
					setPri: 3
					illegalBits: 0
					posn: 195 55
					cycleSpeed: 4
					moveSpeed: 4
					setStep: 6 4
					setCycle: Forward
					setMotion: MoveTo 258 88 self
				)
			)
			(2
				(if (Btst fFakeDoorDown)
					(Bclr fFakeDoorDown)
					((ScriptID 98 19) dispose:)
				)
				(if (not (Btst fPulledChain))
					(yorick setLoop: 3)
				)
				(ego setPri: 7 setMotion: MoveTo 312 109 self)
			)
			(3
				(ego setPri: 15 setMotion: MoveTo 318 116 self)
				((ScriptID 98 14) setCel: 1)
				((ScriptID 98 16) setCel: 1)
				(User canInput: FALSE)
				(door11 setCycle: BegLoop)
			)
			(4
				(Palette PALIntensity 71 255 0)
				(if (not (Btst fPulledChain))
					(yorick setLoop: 3)
				)
				((ScriptID 98 14) setCel: 2)
				((ScriptID 98 16) setCel: 2)
				(ego
					setCycle: Forward
					yStep: 20
					setMotion: MoveTo 316 200 self
				)
			)
			(5
				(Palette PALIntensity 71 255 100)
				(if (not (TakeDamage 5))
					(EgoDead 114 115 2 5 517)
				else
					((ScriptID 98 14) setCel: 0)
					((ScriptID 98 16) setCel: 0)
					(self changeState: 0)
				)
			)
			(6
				(ego
					setLoop: 5
					cel: 0
					setStep: 3 2
					setPri: 9
					posn: (+ (ego x?) 8) (+ (ego y?) 14)
					setMotion: MoveTo 309 114 self
					cycleSpeed: 6
					setCycle: CycleTo 2 1
				)
				(door11 setCycle: BegLoop)
			)
			(7
				(ego setPri: 10 setCel: 3)
				(= ticks 30)
			)
			(8
				(ego setCel: 4)
				(if (not (Btst fPulledChain))
					(yorick setLoop: 1 cel: 0 setCycle: 0 stopUpd:)
					((ScriptID 98 32) setCel: 1)
				)
				(= ticks 30)
			)
			(9
				(ego x: (- (ego x?) 4))
				(HandsOn)
				(ego setLoop: 1 illegalBits: cWHITE)
				(NormalEgo)
				(Bclr fRollingOut)
				(theGame setSpeed: saveSpeed)
				(trap4 setPri: 5)
				(self dispose:)
			)
		)
	)
)

(instance happyFace of Script
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local78 1)
				(SetCurIcon 1)
				(HandsOff)
				(ego setMotion: MoveTo 243 91 self)
			)
			(1
				(Face ego (ScriptID 98 5))
				(door13 setCycle: EndLoop self)
				((ScriptID 98 30)
					illegalBits: 0
					ignoreActors:
					posn: 258 5
					setCycle: Forward
				)
			)
			(2
				((ScriptID 98 30) setMotion: JumpTo 243 31 self)
			)
			(3
				(ego view: 296 setLoop: 6 setCel: 0 setCycle: EndLoop self)
				((ScriptID 98 30) setMotion: JumpTo 220 200 self)
				(door13 setCycle: BegLoop)
			)
			(4
				(Bset fBallConks)
				(Bset fFallingOffLedge)
			)
			(5
				((ScriptID 98 30) posn: 500 500 stopUpd:)
				(= ticks 30)
			)
			(6
				(if (not (Btst fPulledChain))
					((ScriptID 98 32) setCel: 3)
					(yorick setLoop: 3 cel: 0 cycleSpeed: 8 setCycle: Forward)
				)
				(client setScript: 0)
			)
		)
	)
)

(instance goTo2 of Script
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath (+ (ego x?) 25) (ego y?) self)
			)
			(1
				(Disoriented)
				(= cycles 10)
			)
			(2
				(ego
					setPri: 1
					illegalBits: 0
					posn: 33 48
					setMotion: MoveTo 55 50 self
				)
			)
			(3
				(HandsOn)
				(ego illegalBits: cWHITE)
				(Bclr fGoThruDoorway)
				(client setScript: 0)
			)
		)
	)
)

(instance goTo6 of Script
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					setLoop: 3
					setMotion: MoveTo 198 64 self
				)
			)
			(1 (door11 setCycle: BegLoop self))
			(2
				(Disoriented)
				(= ticks 60)
			)
			(3
				(NormalEgo)
				(ego posn: 84 73 setMotion: MoveTo 116 73 self)
			)
			(4
				(HandsOn)
				(User canInput: TRUE)
				(Bclr fGoThruDoorway)
				(client setScript: 0)
			)
		)
	)
)

(instance goTo12 of Script
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= saveSpeed speed)
				(ego
					illegalBits: 0
					setMotion: MoveTo (- (ego x?) 25) (ego y?) self
				)
			)
			(1
				(Disoriented)
				(ego
					view: 296
					setLoop: 8
					cel: 0
					posn: 290 91
					cycleSpeed: 6
					moveSpeed: 6
				)
				((ScriptID 98 24) setCycle: EndLoop self)
			)
			(2
				(ego setCycle: Forward setMotion: MoveTo 238 91 self)
			)
			(3
				(ego setLoop: 9 setCel: 0 posn: 240 91)
				(= ticks 30)
			)
			(4
				(ego setLoop: 1)
				(NormalEgo)
				((ScriptID 98 24) setCycle: BegLoop self)
			)
			(5
				(HandsOn)
				(Bset fNoMoreTalking)
				(Bclr fGoThruDoorway)
				(theGame setSpeed: saveSpeed)
				(client setScript: 0)
			)
		)
	)
)

(instance goTo15 of Script
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					setMotion: MoveTo (- (ego x?) 10) (- (ego y?) 5) self
				)
			)
			(1
				(Disoriented)
				(ego
					posn: 325 107
					setPri: -1
					setMotion: MoveTo 300 107 self
				)
			)
			(2
				(if (Btst fPulledChain)
					(Bset fNoMoreTalking)
				)
				(NormalEgo)
				(HandsOn)
				(Bclr fGoThruDoorway)
				(client setScript: 0)
			)
		)
	)
)

(instance poof of Prop
	(properties
		view 297
		loop 7
	)
)

(instance thingThrown of Actor
	(properties
		z 30
		yStep 10
		view 297
		xStep 15
	)
)

(instance throwIt of Script
	(method (doit)
		;added to fix speed bug
		(if (< (Abs (- gameTime name)) 2) (return))
		(= name gameTime)
		(if
			(and
				(not throwCued)
				(or
					(Btst fOpeningDoor)
					(== (ego onControl: origin) cYELLOW)
					(door11 script?)
				)
			)
			(= throwCued TRUE)
			(= cycles 0)
			(self changeState: 7)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: 0 setMotion: 0)
				(Bset fYorickThrows)
				(User canInput: FALSE)
				(if (Btst fPulledChain)
					(switch (yorick cel?)
						(0
							(poof
								posn: (+ (yorick x?) [yorickX2 0]) (+ (yorick y?) [yorickY2 0])
							)
						)
						(1
							(poof
								posn: (+ (yorick x?) [yorickX2 1]) (+ (yorick y?) [yorickY2 1])
							)
						)
						(2
							(poof
								posn: (+ (yorick x?) [yorickX2 2]) (+ (yorick y?) [yorickY2 2])
							)
						)
						(3
							(poof
								posn: (+ (yorick x?) [yorickX2 3]) (+ (yorick y?) [yorickY2 3])
							)
						)
						(4
							(poof
								posn: (+ (yorick x?) [yorickX2 4]) (+ (yorick y?) [yorickY2 4])
							)
						)
					)
				else
					(yorick
						setLoop: 2
						setCel: ((ScriptID 98 32) cel?)
						stopUpd:
					)
					((ScriptID 98 32) setCel: 3)
					(switch (yorick cel?)
						(0
							(poof
								posn: (+ (yorick x?) [yorickX 0]) (+ (yorick y?) [yorickY 0])
							)
						)
						(1
							(poof
								posn: (+ (yorick x?) [yorickX 1]) (+ (yorick y?) [yorickY 1])
							)
						)
						(2
							(poof
								posn: (+ (yorick x?) [yorickX 2]) (+ (yorick y?) [yorickY 2])
							)
						)
					)
				)
				(poof
					setPri: (+ (yorick priority?) 2)
					ignoreActors:
					init:
					setCycle: CycleTo 3 1 self
				)
			)
			(1
				(if (Btst fPulledChain)
					(switch (yorick cel?)
						(0
							(thingThrown
								posn: (+ (yorick x?) [yorickX2 0]) (+ (yorick y?) [thingY2 0])
							)
						)
						(1
							(thingThrown
								posn: (+ (yorick x?) [yorickX2 1]) (+ (yorick y?) [thingY2 1])
							)
						)
						(2
							(thingThrown
								posn: (+ (yorick x?) [yorickX2 2]) (+ (yorick y?) [thingY2 2])
							)
						)
						(3
							(thingThrown
								posn: (+ (yorick x?) [yorickX2 3]) (+ (yorick y?) [thingY2 3])
							)
						)
						(4
							(thingThrown
								posn: (+ (yorick x?) [yorickX2 4]) (+ (yorick y?) [thingY2 4])
							)
						)
					)
				else
					(switch (yorick cel?)
						(0
							(thingThrown
								posn: (+ (yorick x?) [yorickX 0]) (+ (yorick y?) [thingY 0])
							)
						)
						(1
							(thingThrown
								posn: (+ (yorick x?) [yorickX 1]) (+ (yorick y?) [thingY 1])
							)
						)
						(2
							(thingThrown
								posn: (+ (yorick x?) [yorickX 2]) (+ (yorick y?) [thingY 2])
							)
						)
					)
				)
				(thingThrown
					setLoop: 8
					setCel: (Random 0 5)
					setPri: (ego priority?)
					ignoreActors:
					illegalBits: 0
					init:
					setMotion: Chase ego 20 self
				)
				(poof setCycle: EndLoop)
			)
			(2
				(HandsOff)
				(thingThrown
					setPri: (- (ego priority?) 1)
					setMotion: Chase ego 0 self
				)
			)
			(3
				(Bset 260)
				(ego
					view: 296
					setMotion: 0
					setCycle: 0
					loop:
						(cond 
							((mod (ego loop?) 2) 7)
							((== (ego loop?) 6) 7)
							(else 6)
						)
					setCel: 0
				)
				(if (== (ego loop?) 6)
					(ego setCycle: EndLoop)
				else
					(ego setCycle: CycleTo 1 1)
				)
				(TakeDamage 1)
				(cond 
					((> (ego x?) (yorick x?))
						(if (and (< (ego x?) 313) (< (ego y?) 186))
							(ego posn: (+ (ego x?) 6) (+ (ego y?) 3))
						)
					)
					((and (> (ego x?) 6) (< (ego y?) 186)) (ego posn: (- (ego x?) 6) (+ (ego y?) 3)))
				)
				(if (not (Btst fPulledChain))
					((ScriptID 98 32) setCel: 1)
					(yorick setLoop: 1 setCel: 0 stopUpd:)
				)
				(poof dispose:)
				(= cycles 1)
			)
			(4
				(if (> (ego x?) (yorick x?))
					(thingThrown
						setPri: (cond 
							((> (ego y?) 138) 11)
							((> (ego y?) 115) 8)
							(else 7)
						)
						setMotion: JumpTo (- (ego x?) (Random 30 60)) 210 self
					)
				else
					(thingThrown
						setMotion: JumpTo (+ (ego x?) (Random 30 60)) 210 self
					)
				)
			)
			(5
				(thingThrown dispose:)
				(HandsOn)
				(Bclr 260)
				(NormalEgo)
				(Face ego yorick)
				(User canInput: 1)
				(= cycles 2)
			)
			(6
				(if (and (not (Btst fPulledChain)) (Btst fOpeningDoor))
					((ScriptID 98 32) setCel: 3)
					(yorick setLoop: 3 cel: 0 cycleSpeed: 8 setCycle: Forward)
				)
				(poof dispose:)
				(thingThrown dispose:)
				(User canInput: 1)
				(= cycles 2)
			)
			(7
				(HandsOn)
				(Bclr 271)
				(client setScript: 0)
			)
		)
	)
)

(instance talksAlot of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 80 189 self)
			)
			(1 (ego loop: 2) (= cycles 2))
			(2 (messager say: N_ROOM 0 C_BARDOOR 0 self))
			(3 (Face ego yorick self))
			(4 (= ticks 60))
			(5
				(LoadMany FALSE RES_VIEW 296)
				(ego illegalBits: -32768)
				(messager say: N_ROOM 0 C_MEETYORICK 0 self)
			)
			(6
				(yorick stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance knockOut of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SetCurIcon 1)
				(HandsOff)
				(ego
					setLoop: 1
					setCycle: Reverse
					setMotion: MoveTo 83 91 self
				)
			)
			(1
				(ego setCycle: 0)
				(door3 setCycle: CycleTo 6 1 self)
			)
			(2 (= ticks 12))
			(3
				(ego view: 296 setLoop: 7 setCel: 0)
				(door3 setCel: 7)
				(= ticks 12)
			)
			(4
				(ego setCel: 1 posn: 94 91)
				(door3 setCel: 8)
				(= ticks 12)
			)
			(5
				(door3 setCycle: EndLoop)
				(ego setCel: 2)
				(if (not (Btst fPulledChain))
					((ScriptID 98 32) setCel: 3)
					(yorick setLoop: 3 cel: 0 cycleSpeed: 8 setCycle: Forward)
				)
				(= ticks 12)
			)
			(6
				(ego
					setCel: 3
					setPri: 6
					moveSpeed: 0
					setStep: 10 10
					illegalBits: 0
					setMotion: DPath 135 109 139 178 self
				)
			)
			(7
				(door3 setCel: 0)
				(if (not (TakeDamage 5))
					(EgoDead 114 115 2 5 517)
				else
					(= cycles 10)
				)
			)
			(8
				(Bclr 258)
				(Bclr fOpeningDoor)
				(Bset 260)
				(ego setScript: rollOut)
				(client setScript: 0)
			)
		)
	)
)

(instance takeADive of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not (Btst fPulledChain))
					((ScriptID 98 32) dispose:)
					(yorick view: 298 setLoop: 0 setCel: 0 setCycle: EndLoop self)
				else
					(= ticks 1)
				)
			)
			(1
				(if (not (Btst fPulledChain))
					(= ticks 12)
				else
					(= ticks 1)
				)
			)
			(2
				(if (Btst fYorickLikesYou)
					(messager say: N_ROOM NULL C_YORICKFRIEND)
				)
				(yorick setLoop: 1 setCel: 0 setCycle: MoveCycle @yorickLeavePath self)
			)
			(3
				(yorick dispose:)
				(client view: 292 loop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(4
				(client view: 96 loop: 3 cel: 0 setPri: 3)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance doorFall of Script
	(method (dispose)
		(ego illegalBits: (& (ego illegalBits?) $ffff))
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 0)
				(ego illegalBits: (| (ego illegalBits?) (| cCYAN cMAGENTA)))
				(++ shakeCount)
				((ScriptID 98 20)
					posn: (- ((ScriptID 98 20) x?) 1) ((ScriptID 98 20) y?)
				)
				(= ticks 20)
			)
			(1
				((ScriptID 98 20)
					posn: (+ ((ScriptID 98 20) x?) 1) ((ScriptID 98 20) y?)
				)
				(= ticks 20)
			)
			(2
				(if (< shakeCount 7)
					(= state -1)
					(= ticks 1)
				else
					(= shakeCount 0)
					(= ticks 1)
				)
			)
			(3
				((ScriptID 98 19) ignoreActors: setPri: 1 init:)
				(HandsOff)
				(cond 
					(
					(and (== (ego onControl: origin) cLRED) (not (ego script?)))
						(ego setPri: 15)
						(= register 1)
						((ScriptID 98 20) view: 291 loop: 10 setCel: 0)
						(= ticks 12)
					)
					(((ScriptID 98 13) script?)
						((ScriptID 98 20) setCel: 0)
						((ScriptID 98 19) delete:)
						(client setScript: 0)
					)
					(else (= ticks 1))
				)
			)
			(4
				(if register (ego hide: forceUpd:))
				((ScriptID 98 20) setCycle: EndLoop self)
			)
			(5
				((ScriptID 98 20) view: 96 loop: 1 setCel: 3)
				(if register (= ticks 30) else (self changeState: 10))
			)
			(6
				(if (TakeDamage 10)
					((ScriptID 98 20) setCel: 2)
					(ego
						view: 291
						loop: 12
						cel: 0
						illegalBits: 0
						posn: 110 72
						show:
					)
					(= ticks 12)
				else
					(EgoDead 169 170 5 6 516)
				)
			)
			(7
				((ScriptID 98 20) setCycle: BegLoop self)
			)
			(8 (ego setCycle: EndLoop self))
			(9
				((ScriptID 98 19) dispose:)
				(messager say: N_ROOM NULL C_DOORFALL)
				(= ticks 1)
			)
			(10
				(if (not (ego script?)) (NormalEgo))
				(if register
					(ego setMotion: MoveTo 112 74 self)
				else
					(= ticks 20)
				)
			)
			(11
				(if (!= ((ScriptID 98 20) cel?) 0)
					((ScriptID 98 19) setPri: -1)
					(Bset 264)
				)
				(ego illegalBits: -32768)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance finalExit of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fOpeningDoor)
				((ScriptID 98 19) setPri: 1 setCycle: EndLoop self)
			)
			(1
				(ego illegalBits: 0 setMotion: MoveTo 123 62 self)
			)
			(2
				(Bclr fOpeningDoor)
				(curRoom newRoom: 97)
			)
		)
	)
)

(instance brickWall of Script
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 1)
			)
			(1
				(NormalEgo)
				((ScriptID 98 8) init:)
				(door11 setCycle: EndLoop self)
			)
			(2
				(messager say: N_ROOM NULL C_BRICKWALL)
				(= cycles 4)
			)
			(3
				(door11 setCycle: BegLoop self)
			)
			(4
				((ScriptID 98 8) dispose:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance fallSideways of Script
	(method (dispose)
		(Bset fNoMoreTalking)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not (Btst fPulledChain))
					((ScriptID 98 32) setCel: 3)
					(yorick setLoop: 3 cel: 0 cycleSpeed: 8 setCycle: Forward)
				)
				(if (== client (ScriptID 98 14))
					(client setPri: (- (client priority?) 1))
				)
				(ego
					setPri:
						(cond 
							((< (ego y?) 111) 6)
							((< (ego y?) 135) 10)
							(else 12)
						)
				)
				(cond 
					(local78
						(= local78 0)
						(ego x: (- (ego x?) 24))
					)
					((Btst fFallingOffLedge)
						(ego x: (+ (ego x?) 2))
					)
					(else
						(ego x: (- (ego x?) 5))
					)
				)
				(ego
					view: 517
					setLoop: (if (Btst fFallingOffLedge) 3 else 2)
					cel: 0
					cycleSpeed: 8
					setCycle: CycleTo 2 1 self
				)
				(if (== client (ScriptID 98 15))
					(ego setMotion: MoveTo (+ (ego x?) 12) (ego y?) self)
				)
			)
			(1
				(Palette PALIntensity 71 255 0)
				(ego
					yStep: 10
					illegalBits: 0
					setCycle: EndLoop
					setMotion: MoveTo (ego x?) 220 self
				)
			)
			(2
				(Palette PALIntensity 71 255 100)
				(if (not (TakeDamage 5))
					(EgoDead 114 115 2 5 517)
				else
					(Bclr fFallingOffLedge)
					(Bclr fOpeningDoor)
					(Bset fRollingOut)
					(if (Btst fFallTrapdoor)
						(Bclr fFallTrapdoor)
						(ego setScript: rollOut)
						(if (== client (ScriptID 98 14))
							(client setPri: (+ (client priority?) 2))
							((ScriptID 98 16)
								setCel: 0
								setPri: (+ ((ScriptID 98 16) priority?) 2)
							)
						)
						(client setCel: 0 setScript: 0)
					else
						(ego setScript: rollOut)
					)
				)
			)
		)
	)
)

(instance trapFall of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== client trap4)
					(ego setPri: 15)
				)
				(if (!= client (ScriptID 98 15))
					(client setPri: (- (client priority?) 1))
				)
				(cond 
					((== client (ScriptID 98 14))
						(client setCel: 1)
						((ScriptID 98 16)
							setPri: (- ((ScriptID 98 16) priority?) 2)
						)
						(= ticks 12)
					)
					((and (== register 2) (!= client trap4))
						(= ticks 12)
					)
					(else
						(client setCycle: EndLoop self)
					)
				)
			)
			(1
				(if (== client (ScriptID 98 14))
					(client setCycle: EndLoop self)
					((ScriptID 98 16) setCycle: EndLoop)
				else
					(= ticks 12)
				)
			)
			(2
				(cond 
					((== client trap4)
						(Bset fFallTrap4)
						(= register 0)
						(self dispose:)
					)
					((== client (ScriptID 98 13))
						(cond 
							((== register 1) (client setScript: fallUpscreen 0 1))
							((== register 2)
								(client setScript: fallDownscreen)
							)
							((== register 3)
								(client setScript: fallSideways)
							)
							(else
								(client setScript: fallDownscreen)
							)
						)
						(= register 0)
					)
					(else
						(= register 0)
						(if (and (< (ego x?) 295) (== client (ScriptID 98 14)))
							(ego x: (+ (ego x?) 25))
						)
						(client setScript: fallSideways)
					)
				)
			)
		)
	)
)

(instance roomMusic of Sound
	(properties
		number 82
		priority 1
		loop -1
	)
)

(instance yorickTalker of Talker
	(properties
		x 10
		y 10
		view 1096
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2096)
		(PalVary PALVARYTARGET 2096)
		(AssertPalette 1096)
		(= font userFont)
		(super init: yorickBust yorickEye yorickMouth &rest)
	)
	
	(method (dispose)
		(LoadMany FALSE RES_VIEW 1096)
		(super dispose:)
	)
)

(instance yorickBust of Prop
	(properties
		view 1096
	)
)

(instance yorickMouth of Prop
	(properties
		nsTop 39
		nsLeft 34
		view 1096
		loop 1
	)
)

(instance yorickEye of Prop
	(properties
		nsTop 25
		nsLeft 33
		view 1096
		loop 2
	)
)
