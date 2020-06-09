;;; Sierra Script 1.0 - (do not remove this comment)
(script# 141)
(include game.sh) (include "141.shm")
(use Main)
(use Teller)
(use Procs)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm141 0
	baronTalker 1
	bernieTalker 2
)

(local
	[local0 6] = [0 10 -11 8 -9 999]
	[local6 5] = [0 7 -12 15 999]
	[local11 3] = [0 -16 999]
	[local14 4] = [0 18 14 999]
	[local18 4] = [0 13 17 999]
	[local22 6] = [0 22 20 21 19 999]
	[local28 7]
	[local35 4]
	[local39 2] = [0 999]
	[local41 7] = [0 -11 -9 -12 -16 999]
	local48
	local49
	oldCycleSpeed
	oldSortedFeatures
)
(instance rm141 of Room
	(properties
		noun N_ROOM
		picture 141
		style DISSOLVE
	)
	
	(method (init)
		(= [local28 0] @local0)
		(= [local28 1] @local6)
		(= [local28 2] @local11)
		(= [local28 3] @local14)
		(= [local28 4] @local18)
		(= [local28 5] 999)
		(= [local35 0] @local22)
		(Load VIEW 141)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						272 189
						0 189
						0 0
						319 0
						319 166
						312 166
						303 159
						300 147
						290 140
						280 139
						265 141
						259 150
						253 156
						242 158
						232 156
						226 149
						225 142
						222 145
						214 146
						93 125
						10 154
						137 188
						272 188
					yourself:
				)
		)
		(super init:)
		(= oldSortedFeatures useSortedFeatures)
		(= useSortedFeatures FALSE)
		(= disabledActions (| disabledActions (| ACTION_RUN ACTION_SNEAK)))
		(ego actions: egoActions init:)
		(SolvePuzzle f141MeetBaron 10)
		(StatusLine enable:)
		(guard1 addToPic:)
		(guard2 addToPic:)
		(self
			setFeatures:
				tapestry
				alcove
				crest
				pillars
				stainedGlass
				couch
				vase
				brasier
				piglet
				wine
				table
				throne
		)
		;UPGRADE
;;;		(tapestry init:)
;;;		(alcove init:)
;;;		(crest init:)
;;;		(pillars init:)
;;;		(stainedGlass init:)
;;;		(couch init:)
;;;		(vase init:)
;;;		(brasier init:)
;;;		(piglet init:)
;;;		(wine init:)
;;;		(table init:)
;;;		(throne init:)
		
		(guard3 init: setPri: 4 stopUpd:)
		(baronTeller init: baron @local0 @local28 @local41)
		(baron setPri: 5 actions: baronTeller init:)
		(bernardTeller init: son @local22 @local35 @local39)
		(son setPri: 4 actions: bernardTeller init: stopUpd:)
		(if (or (== egoGait MOVE_SNEAK) (== egoGait MOVE_RUN))
			(ChangeGait MOVE_WALK TRUE)
		)
		(= freeMeals 1)
		(self setScript: egoEnters)
	)
	
	(method (doit)
		(if (and (& (ego onControl: origin) cYELLOW) (not (ego script?)))
			(ego setScript: guardBlocks)
		else
			(super doit:)
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(= useSortedFeatures oldSortedFeatures)
		(= disabledActions FALSE)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(messager say: N_ROOM V_FLAME)
			)
			(V_TRIGGER
				(messager say: N_ROOM V_FLAME)
			)
			(V_DAZZLE
				(messager say: N_ROOM V_FLAME)
			)
			(V_ROCK
				(messager say: N_ROOM V_FLAME)
			)
			(V_DAGGER
				(messager say: N_ROOM V_FLAME)
			)
			(V_OPEN
				(messager say: N_ROOM V_FLAME)
			)
			(V_CALM
				(messager say: N_ROOM V_FLAME)
			)
			(V_DETECT
				(messager say: N_ROOM V_FLAME)
			)
			(V_FETCH
				(messager say: N_ROOM V_FLAME)
			)
			(V_ZAP
				(messager say: N_ROOM V_FLAME)
			)
			(V_SLEEP
				(curRoom setScript: leaveRoom)
			)
			(V_LOOK
				(messager say: N_ROOM V_LOOK)
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
					(messager say: N_ROOM V_LOOK C_EGOACTION)
				)
				(V_DO
					(messager say: N_ROOM V_DO C_EGOACTION)
				)
				(V_TALK
					(messager say: N_ROOM V_TALK C_EGOACTION)
				)
				(else
					(return FALSE)
				)
			)
		)
	)
)

(instance baron of Actor
	(properties
		x 51
		y 130
		noun N_BARON
		view 141
		loop 2
		illegalBits $0000
	)
	
	(method (init)
		(self
			setStep: 2 2
			signal: (| ignrAct ignrHrz fixPriOn)
		)
		(super init:)
	)
)

(instance baronTeller of Teller
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb V_FLAME V_TRIGGER V_DAZZLE)
				(messager say: N_ROOM V_FLAME)
			)
			(V_TALK
				(if local48
					(curRoom setScript: leaveRoom)
				else
					(SolvePuzzle f141TalkToBaron 3)
					(super doVerb: theVerb &rest)
				)
			)
			(V_LOOK
				(messager say: N_BARON V_LOOK)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
		(return 1)
	)
)

(instance son of Actor
	(properties
		x 91
		y 122
		noun N_BARNARD
		view 141
		loop 4
		illegalBits $0000
	)
	
	(method (init)
		(self
			setStep: 2 2
			signal: (| ignrAct ignrHrz fixPriOn)
		)
		(super init:)
	)
)

(instance bernardTeller of Teller
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb V_FLAME 77 78)
				(messager say: N_ROOM V_FLAME)
			)
			(V_TALK
				(super doVerb: theVerb &rest)
			)
			(V_LOOK
				(messager say: 3 1)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
		(return TRUE)
	)
)

(instance tapestry of Feature
	(properties
		noun N_TAPESTRY
		onMeCheck cBLUE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb V_FLAME V_TRIGGER V_DAZZLE)
				(messager say: N_ROOM V_FLAME)
			)
			(V_LOOK
				(messager say: N_TAPESTRY V_LOOK)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance alcove of Feature
	(properties
		noun N_ALCOVE
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb V_FLAME V_TRIGGER V_DAZZLE)
				(messager say: N_ROOM V_FLAME)
			)
			(V_LOOK
				(messager say: N_ALCOVE V_LOOK)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance crest of Feature
	(properties
		noun N_CREST
		onMeCheck cCYAN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb V_FLAME V_TRIGGER V_DAZZLE)
				(messager say: N_ROOM V_FLAME)
			)
			(V_LOOK
				(messager say: N_CREST V_LOOK)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance pillars of Feature
	(properties
		noun N_PILLARS
		onMeCheck cRED
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb V_FLAME V_TRIGGER V_DAZZLE)
				(messager say: N_ROOM V_FLAME)
			)
			(V_LOOK
				(messager say: N_PILLARS V_LOOK)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance stainedGlass of Feature
	(properties
		noun N_STAINEDGLASS
		onMeCheck cMAGENTA
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb V_FLAME V_TRIGGER V_DAZZLE)
				(messager say: N_ROOM V_FLAME)
			)
			(V_LOOK
				(messager say: N_STAINEDGLASS V_LOOK)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance couch of Feature
	(properties
		noun N_COUCH
		onMeCheck cBROWN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb V_FLAME V_TRIGGER V_DAZZLE)
				(messager say: N_ROOM V_FLAME)
			)
			(V_LOOK
				(messager say: N_COUCH V_LOOK)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance vase of Feature
	(properties
		noun N_VASE
		onMeCheck cLGREY
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb V_FLAME V_TRIGGER V_DAZZLE)
				(messager say: N_ROOM V_FLAME)
			)
			(V_LOOK
				(messager say: N_VASE V_LOOK)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance brasier of Feature
	(properties
		noun N_BRAZIER
		onMeCheck cGREY
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb V_FLAME V_TRIGGER V_DAZZLE)
				(messager say: N_ROOM V_FLAME)
			)
			(V_LOOK
				(messager say: N_BRAZIER V_LOOK)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance piglet of Feature
	(properties
		noun N_PIGLET
		onMeCheck cLGREEN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb V_FLAME V_TRIGGER V_DAZZLE)
				(messager say: N_ROOM V_FLAME)
			)
			(V_LOOK
				(messager say: N_PIGLET V_LOOK)
			)
			(V_DO
				(messager say: N_TABLE V_DO)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance wine of Feature
	(properties
		noun N_WINE
		onMeCheck cLCYAN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb V_FLAME V_TRIGGER V_DAZZLE)
				(messager say: N_ROOM V_FLAME)
			)
			(V_LOOK
				(messager say: N_WINE V_LOOK)
			)
			(V_DO
				(messager say: N_ROOM V_DO)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance table of Feature
	(properties
		noun N_TABLE
		onMeCheck cLRED
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb V_FLAME V_TRIGGER V_DAZZLE)
				(messager say: N_ROOM V_FLAME)
			)
			(V_LOOK
				(messager say: N_TABLE V_LOOK)
			)
			(V_DO
				(messager say: N_TABLE V_DO)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance throne of Feature
	(properties
		noun N_THRONE
		onMeCheck cLMAGENTA
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb V_FLAME V_TRIGGER V_DAZZLE)
				(messager say: N_ROOM V_FLAME)
			)
			(V_LOOK
				(messager say: N_THRONE V_LOOK)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance guard3 of Prop
	(properties
		x 296
		y 151
		noun N_GUARD
		view 141
		loop 6
		cel 1
	)
	
	(method (doVerb theVerb)
		(guard1 doVerb: theVerb &rest)
	)
)

(instance guard1 of View
	(properties
		x 20
		y 141
		noun N_GUARD
		view 141
		loop 1
		priority 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb V_FLAME V_TRIGGER V_DAZZLE)
				(messager say: N_ROOM V_FLAME)
			)
			(V_LOOK
				(messager say: N_GUARD V_LOOK)
			)
			(V_TALK
				(messager say: N_GUARD V_TALK)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance guard2 of View
	(properties
		x 136
		y 118
		noun N_GUARD
		view 141
		loop 1
		cel 1
		priority 3
	)
)

(instance egoEnters of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 10)
			)
			(1
				(ego init: posn: 299 184 setMotion: MoveTo 155 162 self)
			)
			(2
				(Face ego baron)
				(= oldCycleSpeed (ego cycleSpeed?))
				(= seconds 2)
			)
			(3
				(ego
					view: 508
					loop: 0
					cel: 0
					cycleSpeed: 12
					forceUpd:
					setCycle: EndLoop self
				)
			)
			(4
				(NormalEgo 7 4)
				(theGame setCursor: waitCursor)
				(ego cycleSpeed: oldCycleSpeed)
				(= seconds 2)
			)
			(5
				(client setScript: openingSpeech)
			)
		)
	)
)

(instance openingSpeech of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load VIEW 1141)
				(baron setCycle: CycleTo 7 1 self)
			)
			(1
				(= seconds 2)
			)
			(2
				(baron stopUpd:)
				(messager say: N_BARON NULL C_MEETBARON 0 self)
			)
			(3
				(Load VIEW 1317)
				(son setCycle: EndLoop self)
			)
			(4
				(= seconds 1)
			)
			(5
				(son stopUpd:)
				(messager say: N_BARNARD NULL C_MEETBARON 0 self)
			)
			(6
				(Load VIEW 1141)
				(= seconds 2)
			)
			(7
				(if (Btst fKoboldDead)
					(messager say: N_BARON NULL C_KILLEDKOBOLD 0 self)
				else
					(self cue:)
				)
			)
			(8
				(if (Btst fKoboldDead)
					(self cue:)
				else
					(client setScript: becomeHero)
				)
			)
			(9
				(= seconds 2)
			)
			(10
				(messager say: N_BARON NULL C_MEETBARON 4 self)
			)
			(11
				(Load VIEW 1317)
				(= ticks 120)
			)
			(12
				(messager say: N_BARNARD NULL C_KILLEDKOBOLD 1 self)
			)
			(13
				(client setScript: becomeHero)
			)
		)
	)
)

(instance becomeHero of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load VIEW 1141)
				(= seconds 2)
			)
			(1
				(messager say: N_BARON NULL C_KEEPITUP 0 self)
			)
			(2
				(= seconds 2)
			)
			(3
				(messager say: N_BARON NULL C_MEETBARON 4 self)
			)
			(4
				(Load VIEW 1317)
				(= ticks 120)
			)
			(5
				(messager say: N_BARNARD NULL C_KEEPITUP 1 self)
			)
			(6
				(Load VIEW 1141)
				(= seconds 1)
			)
			(7
				(messager say: N_BARON NULL C_EARNREWARD 0 self)
			)
			(8
				(cSound fade:)
				(= seconds 2)
			)
			(9
				(messager say: N_BARON NULL C_ANYQUESTIONS 1 self)
			)
			(10
				(client setScript: answerQs)
			)
		)
	)
)

(instance answerQs of Script
	
	(method (init)
		(HandsOn)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: origin) cLBLUE)
			(client setScript: leaveRoom)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks 6)
			)
			(1
				(if (not local48)
					(self changeState: 0)
				else
					(client setScript: leaveRoom)
				)
			)
		)
	)
)

(instance leaveRoom of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego get: iGold 50)
				(HandsOff)
				(ego setCycle: 0 setMotion: 0)
				(= ticks 24)
			)
			(1
				(NormalEgo 1 4)
				(= ticks 12)
			)
			(2
				(Face ego baron self)
			)
			(3
				(messager say: N_BARON NULL C_LEAVEROOM 1 self)
			)
			(4
				(ego setMotion: MoveTo 350 188 self)
			)
			(5
				(NextDay)
				(FixTime 5)
				(= [egoStats STAMINA] (MaxStamina))
				(TakeDamage -16)
				(UseMana -16)
				(= ticks 100)
			)
			(6
				(if (and (Btst fBabaCurse) (not (IsObject fastCast)))
					(HandsOff)
					(messager say: N_ROOM NULL C_BABACURSE 0 self)
				else
					(self cue:)
				)
			)
			(7
				(if (and (Btst fBabaCurse) (not (IsObject fastCast)))
					(Bclr fBabaCurse)
					(EgoDead C_DIE_BABA_CURSE C_DIE_BABA_CURSE_TITLE)
				else
					(self cue:)
				)
			)
			(8
				(curRoom newRoom: 41)
			)
		)
	)
)

(instance guardBlocks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: 0 setMotion: 0)
				(guard3 startUpd:)
				(= ticks 6)
			)
			(1
				(guard3 setCycle: EndLoop self)
			)
			(2
				(messager say: N_ROOM NULL C_GUARDBLOCKS 1 self)
			)
			(3
				(guard3 setCycle: BegLoop)
				(ego
					setCycle: Walk
					setMotion: PolyPath (ego x?) (+ (ego y?) 20) self
				)
			)
			(4
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance baronTalker of Talker
	(properties
		x 10
		y 10
		view 1141
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2141)
		(PalVary PALVARYTARGET 2141)
		(kernel_128 1141)
		(= font userFont)
		(super init: baronBust baronEye baronMouth &rest)
	)
)

(instance baronBust of Prop
	(properties
		view 1141
	)
)

(instance baronMouth of Prop
	(properties
		nsTop 44
		nsLeft 42
		view 1141
		loop 1
	)
)

(instance baronEye of Prop
	(properties
		nsTop 26
		nsLeft 46
		view 1141
		loop 3
	)
)

(instance bernieTalker of Talker
	(properties
		x 10
		y 10
		view 1014
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2014)
		(PalVary PALVARYTARGET 2014)
		(kernel_128 1014)
		(= font userFont)
		(super init: bernieBust bernieEye bernieMouth &rest)
	)
)

(instance bernieBust of Prop
	(properties
		view 1014
	)
)

(instance bernieEye of Prop
	(properties
		nsTop 33
		nsLeft 45
		view 1014
		loop 2
	)
)

(instance bernieMouth of Prop
	(properties
		nsTop 53
		nsLeft 45
		view 1014
		loop 1
	)
)
