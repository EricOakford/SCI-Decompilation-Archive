;;; Sierra Script 1.0 - (do not remove this comment)
(script# 41)
(include game.sh) (include "41.shm")
(use Main)
(use Teller)
(use Procs)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm41 0
)

;control for the stairs
(define cSTAIRS	$007e)	; (| cBLUE cGREEN cCYAN cRED cMAGENTA cBROWN))

(local
	lGuardTellMainBranch = [
		STARTTELL
		C_CASTLE
		C_DAUGHTER
		C_NAME_FRED
		C_BARRACKS
		C_BALD_SPOT_LEFT
		C_BARON
		ENDTELL
		]
	[lGuardTellTree 9]
	lGuardTellKeys = [
		STARTTELL
		ENDTELL
		]
	rGuardTellMainBranch = [
		STARTTELL
		C_CASTLE
		C_DAUGHTER
		13
		C_BARRACKS
		-11		;C_BALD_SPOT_RIGHT
		C_BARON
		ENDTELL
		]
	[rGuardTellTree 9]
	rGuardTellKeys = [0 999 1]
	baldSpotTimer
	frederickSays
	pierreSays
	[local43 2]
	nearPierre
	nearFrederick
	beenInside
	enterX
	local49
	stepControl
	local51
	mentionedBaldSpot
	soundObj
)
(instance rm41 of Room
	(properties
		noun N_ROOM
		picture 41
	)
	
	(method (init &tmp soundNum)
		(= [lGuardTellTree 0] @lGuardTellMainBranch)
		(= [lGuardTellTree 1] ENDTELL)
		(= [rGuardTellTree 0] @rGuardTellMainBranch)
		(= [lGuardTellTree 1] ENDTELL)
		(Load RES_VIEW 41)
		(= soundNum (if Night 32 else 25))
		(if
			(or
				(== (theMusic prevSignal?) -1)
				(!= (theMusic number?) soundNum)
			)
			(theMusic stop: number: soundNum loop: -1 priority: 0 play:)
		)
		(super init: &rest)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 146
						284 159
						223 160
						165 131
						106 136
						88 189
						0 189
					yourself:
				)
		)
		(theGround
			init:
;;;			setOnMeCheck: ftrControl cLGREY
		)
		(StatusLine enable:)
		(= beenInside (== prevRoomNum 141))
		(if (and (Btst fSavedBarnard) (not beenInside) (not (Btst fBarnardReward)))
			(self horizon: 130)
		)
		(if (not Night)
			(rguardTeller init: rGuard @rGuardTellMainBranch @rGuardTellTree @rGuardTellKeys)
			(rGuard init: actions: rguardTeller stopUpd:)
			(guardTeller init: lGuard @lGuardTellMainBranch @lGuardTellTree @lGuardTellKeys)
			(lGuard init: actions: guardTeller stopUpd:)
		)
		(lDoor init: stopUpd:)
		(rDoor init: stopUpd:)
		(= enterX (ego x?))
		(ego
			actions: egoActions
			posn: 163 260
			setScript: egoEnters
			init:
		)
	)
	
	(method (doit &tmp thisControl)
		(cond 
			(
				(and
					(= thisControl (ego onControl: origin))
					(not (== thisControl cBLACK))
					(& thisControl cSTAIRS)
					(not (ego script?))
					(not (curRoom script?))
				)
				(= stepControl thisControl)
				(curRoom setScript: doTheSteps)
			)
			((and (not (ego script?)) (ego edgeHit?))
				(ego setScript: egoExits)
			)
			(else 0)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset fBeenIn41)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_OPEN
				(curRoom setScript: doTheOpen)
			)
			(V_LOOK
				(messager say: N_ROOM V_LOOK NULL)
			)
			(V_DO
				(messager say: N_ROOM V_DO NULL)
			)
			(V_SLEEP
				(curRoom setScript: sleepAround)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance egoActions of Actions
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_LOOK
					(messager say: N_EGO V_LOOK NULL)
				)
				(V_DO
					(messager say: N_EGO V_DO NULL)
				)
				(else
					(return FALSE)
				)
			)
		)
	)
)

(instance theGround of Feature
	(properties
		x 209
		y 180
		noun N_GROUND
		onMeCheck cLGREY
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_GROUND V_LOOK NULL)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance lGuard of Actor
	(properties
		x 89
		y 146
		noun N_LGUARD
		view 41
		illegalBits $0000
	)
	
	(method (doit)
		(if (or (not (Btst fSavedBarnard)) (Btst fBarnardReward))
			(if
				(and
					(not (== prevRoomNum 141))
					(not nearFrederick)
					(< (ego y?) 142)
					(< 99 (ego x?))
					(< (ego x?) 209)
				)
				(= nearFrederick 1)
				(= frederickSays 12)
				(lGuard setScript: lGuardTalks 0 0)
			)
			(if
				(or
					(< (ego x?) 78)
					(< 228 (ego x?))
					(> (ego y?) 155)
				)
				(= nearFrederick 0)
			)
		)
		(super doit:)
	)
)

(instance guardTeller of Teller
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_GUARDS V_LOOK)
			)
			(V_TALK
				(super doVerb: theVerb &rest)
			)
			(V_FLAME
				(messager say: N_BADIDEA 59)
			)
			(V_ROCK (EgoDead 92 93 0 0 503))
			;The death icon was originally the "Hero in Jail" duplicated from view 503,
			; but the duplicate was removed from view 39 in the VGA remake,
			; showing a "tiny guard" instead.
			; Unfortunately, the correct icon is too large to fit with the message,
			; causing the game to crash with an "Invalid Rectangle" error, 
			; so the "Hero holding his belly in pain" icon (as used at the barracks) appears in its stead.
			(V_DAGGER
				(EgoDead 92 93 0 0 503)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
		(return TRUE)
	)
)

(instance rGuard of Actor
	(properties
		x 191
		y 133
		noun N_RGUARD
		view 41
		loop 1
		illegalBits $0000
	)
	
	(method (doit)
		(if (or (not (Btst fSavedBarnard)) (Btst fBarnardReward))
			(if
				(and
					(not (== prevRoomNum 141))
					(< (ego x?) 78)
					(< 208 (ego x?))
					(> (ego y?) 141)
				)
				(= nearPierre 0)
			)
			(if baldSpotTimer
				(-- baldSpotTimer)
			)
		)
		(super doit:)
	)
)

(instance rguardTeller of Teller
	(method (doChild)
		(if (== query -11)
			(= mentionedBaldSpot TRUE)
		else
			(super doChild: query)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_RGUARD V_LOOK NULL)
			)
			(V_TALK
				(if (== mentionedBaldSpot TRUE)
					(messager say: N_RGUARD V_TALK C_WONT_TALK 1)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(V_FLAME (messager say: N_BADIDEA 59))
			(V_ROCK (EgoDead 92 93 0 0 503))
			(V_DAGGER (EgoDead 92 93 0 0 503))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
		(return TRUE)
	)
)

(instance lHead of Prop
	(properties
		x 87
		y 146
		z 41
		noun N_LGUARD
		view 41
		loop 2
	)
)

(instance rHead of Prop
	(properties
		x 193
		y 133
		z 41
		noun N_GUARDS
		view 41
		loop 5
	)
)

(instance lDoor of Prop
	(properties
		x 92
		y 53
		noun N_DOOR
		view 41
		loop 3
		cycleSpeed 12
	)
	
	(method (init)
		(if (== prevRoomNum 141)
			(self cel: 3 setPri: 2 ignoreActors:)
		else
			(self setPri: 2 cel: 0)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if Night
					(messager say: N_DOOR V_LOOK C_NIGHT)
				else
					(messager say: N_DOOR V_LOOK C_DAY)
				)
			)
			(V_DO
				(messager say: N_DOOR V_LOCKPICK C_NIGHT)
			)
			(V_LOCKPICK
				(if Night
					(messager say: N_DOOR V_LOCKPICK C_NIGHT)
				else
					(messager say: N_DOOR V_LOCKPICK C_DAY)
				)
			)
			(V_THIEFKIT
				(if Night
					(messager say: N_DOOR V_THIEFKIT C_NIGHT)
				else
					(messager say: N_DOOR V_THIEFKIT C_DAY)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rDoor of Prop
	(properties
		x 163
		y 52
		view 41
		loop 4
		cycleSpeed 12
	)
	
	(method (init)
		(if (== prevRoomNum 141)
			(self cel: 3 setPri: 2 ignoreActors:)
		else
			(self setPri: 2 cel: 0)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(lDoor doVerb: theVerb &rest)
	)
)

(instance light of Prop
	(properties
		x 129
		y 68
		noun N_LIGHT
		view 41
		loop 6
	)
)

(instance leaveHall of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(light init: setPri: 1 setCycle: Forward)
				(ego
					ignoreActors: 1
					posn: 126 126
					setMotion: MoveTo 144 139 self
				)
			)
			(1
				(NormalEgo)
				(= ticks 30)
			)
			(2
				(messager say: N_ROOM NULL C_EXIT_CASTLE 1 self)
			)
			(3
				(lDoor setCycle: BegLoop self)
				(rDoor setCycle: BegLoop self)
			)
			(4)
			(5
				(doorSnd number: 84 play:)
				(= cycles 2)
			)
			(6
				(HandsOn)
				(light setCycle: 0 stopUpd: dispose:)
				(lDoor ignoreActors: 0)
				(rDoor ignoreActors: 0)
				(Bset fBarnardReward)
				(= beenInside 0)
				(client setScript: 0)
			)
		)
	)
)

(instance sleepAround of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_ROOM V_SLEEP NULL 1 self)
			)
			(1
				(if (and (< 750 Clock) (< Clock 2550))
					(FixTime 21)
				)
				(= seconds 3)
			)
			(2 (curRoom newRoom: 37))
		)
	)
)

(instance doorSnd of Sound)

(instance doTheSteps of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(switch stepControl
					(cBLUE
						(ego setMotion: PolyPath 250 162 self)
					)
					(cGREEN
						(ego setMotion: PolyPath 195 187 self)
					)
					(cCYAN
						(ego setLoop: 2 setMotion: PolyPath (ego x?) 187 self)
					)
					(cRED
						(ego setMotion: PolyPath 171 138 self)
					)
					(cMAGENTA
						(ego setMotion: PolyPath 149 137 self)
					)
					(cBROWN
						(ego
							setLoop: 3
							setMotion: PolyPath (+ (ego x?) 5) 147 self
						)
					)
					(else
						(self cue:)
					)
				)
			)
			(2
				(if (not (lGuard script?))
					(HandsOn)
				)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance egoEnters of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(NormalEgo)
				(HandsOff)
				(= ticks 10)
			)
			(1
				(switch prevRoomNum
					(38
						(ego posn: 18 240 setMotion: MoveTo 200 187 self)
					)
					(39
						(if (and (not Night) (Btst fSavedBarnard) (not (Btst fBarnardReward)))
							(ego posn: 160 240 setMotion: MoveTo 160 178 self)
						else
							(ego posn: enterX 240)
							(cond 
								((< enterX 10)
									(ego setMotion: MoveTo (+ enterX 25 self) 185)
								)
								((> enterX 310)
									(ego setMotion: MoveTo (- enterX 25) 185 self)
								)
								(else
									(ego setMotion: MoveTo enterX 185 self)
								)
							)
						)
					)
					(40
						(ego setMotion: MoveTo 220 185 self)
					)
					(141 (ego setScript: leaveHall))
					(else 
						(ego setMotion: MoveTo 160 187 self)
					)
				)
			)
			(2
				(ego
					setMotion: MoveTo (- (ego x?) 1) (- (ego y?) 1) self
				)
			)
			(3
				(if (not Night)
					(if
					(and (Btst fSavedBarnard) (not beenInside) (not (Btst fBarnardReward)))
						(theMusic prevSignal: 0)
						(ego setScript: GuardsTrumpet self)
					else
						(self cue:)
					)
				else
					(self cue:)
				)
			)
			(4
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
				(= ticks 10)
			)
			(1
				(switch (ego edgeHit?)
					(EAST
						(ego setMotion: MoveTo 340 (+ (ego y?) 10) self)
					)
					(SOUTH
						(ego setMotion: MoveTo (ego x?) 240 self)
					)
					(WEST
						(ego setMotion: MoveTo -20 (ego y?) self)
					)
				)
			)
			(2
				(switch (ego edgeHit?)
					(EAST (curRoom newRoom: 40))
					(SOUTH (curRoom newRoom: 39))
					(WEST (curRoom newRoom: 38))
				)
			)
		)
	)
)

(instance guardsGreet of Script
	(method (doit)
		(super doit: &rest)
		(if
			(or
				(== (theMusic prevSignal?) 10)
				(== (theMusic prevSignal?) 20)
			)
			(theMusic prevSignal: 0)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theGame setCursor: waitCursor)
				(= ticks 10)
			)
			(1
				(light init: setPri: 1)
				(lGuard cycleSpeed: 6 setCycle: EndLoop self)
			)
			(2
				(lGuard stopUpd:)
				(theMusic number: 96 loop: -1 priority: 5 play:)
			)
			(3
				(rGuard cycleSpeed: 6 setCycle: EndLoop self)
			)
			(4 (rGuard stopUpd:))
			(5
				(theMusic pause: 1)
				(lGuard setCycle: BegLoop)
				(rGuard setCycle: BegLoop self)
			)
			(6
				(lGuard stopUpd:)
				(rGuard stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance GuardsTrumpet of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(theGame setCursor: waitCursor)
				(self setScript: guardsGreet self)
			)
			(2
				(HandsOff)
				(theGame setCursor: waitCursor)
				(theMusic pause: 0)
				(messager say: N_ROOM NULL C_INVITED 1 self)
			)
			(3
				(light setCycle: Forward)
				(lDoor setCycle: EndLoop self)
				(rDoor setCycle: EndLoop self)
				(doorSnd number: 90 loop: 1 play:)
			)
			(4)
			(5
				(doorSnd stop:)
				(lDoor stopUpd:)
				(rDoor stopUpd:)
				(= seconds 2)
			)
			(6
				(ego
					;if ego's sneaking, he won't get stuck now. The Mac version fixed this bug.
					illegalBits: 0
					setMotion: MoveTo 178 174 self
				)
			)
			(7
				(ego setLoop: 7 setMotion: MoveTo 168 153 self)
			)
			(8
				(ego setLoop: -1 setMotion: MoveTo 111 111 self)
			)
			(9
				(NormalEgo)	;will restore the old illegalBits
				(curRoom newRoom: 141)
			)
		)
	)
)

(instance lGuardTalks of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(user canInput: FALSE)
				(user canControl: FALSE)
				(if
					(and
						(not nearPierre)
						(< (ego y?) 140)
						(< 99 (ego x?))
						(< (ego x?) 209)
					)
					(HandsOff)
					(= nearPierre 1)
					(= pierreSays 13)
					(client setScript: rGuardTalks)
				else
					(if (not local51)
						(HandsOff)
						(lHead init: setCycle: Forward)
					)
					(= ticks 50)
				)
			)
			(2
				(HandsOff)
				(if (not local51)
					(switch register
						(0
							(HandsOff)
							(messager say: N_ROOM NULL C_NO_ENTRY 1 self)
						)
						(1
							(HandsOff)
							(messager say: N_ROOM NULL C_NAMES 1 self)
						)
					)
				else
					(HandsOff)
					(self cue:)
				)
				(= local51 1)
			)
			(3
				(lHead setCycle: 0 dispose:)
				(user canInput: TRUE)
				(user canControl: TRUE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance rGuardTalks of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(HandsOff)
				(user canInput: FALSE)
				(user canControl: FALSE)
				(rHead init: setCycle: Forward)
				(= ticks 50)
			)
			(2
				(switch register
					(0
						(messager say: N_ROOM NULL C_NO_ENTRY 1 self)
					)
					(1
						(messager say: N_ROOM NULL C_NAMES 1 self)
					)
				)
			)
			(3
				(rHead setCycle: 0 dispose:)
				(user canInput: TRUE)
				(user canControl: TRUE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance doTheOpen of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(ego setMotion: PolyPath 250 172 self)
			)
			(2
				(= seconds 1)
			)
			(3
				(theGame setCursor: waitCursor TRUE)
				(ego setMotion: 0 setHeading: 270 self)
			)
			(4
				(ego
					view: 521
					setLoop: 0
					setCel: 0
					setPri: (ego priority?)
					cycleSpeed: 12
					setCycle: CycleTo 2 1 self
				)
			)
			(5
				((= soundObj (Sound new:))
					number: 28
					priority: 6
					init:
					play:
				)
				(ego setCycle: EndLoop self)
			)
			(6
				(soundObj stop: dispose:)
				(ego view: 4 loop: 7 setCel: 0)
				(= ticks 20)
			)
			(7
				(if Night
					(messager say: N_DOOR V_LOCKPICK C_NIGHT 1 self)
				else
					(messager say: N_DOOR V_LOOK C_DAY 1 self)
				)
			)
			(8
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)
