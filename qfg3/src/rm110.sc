;;; Sierra Script 1.0 - (do not remove this comment)
(script# 110)
(include game.sh) (include "110.shm")
(use Main)
(use Talker)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm110 0
	azizaTalker 1
)

(local
	local0
	local1
)
(instance rm110 of Room
	(properties
		picture 110
		style PIXELDISSOLVE
	)
	
	(method (init)
		(theIconBar disable:)
		(HandsOff)
		(Load RES_SOUND 281)
		(self setRegions: INTRO)
		(aziza init:)
		(super init: &rest)
		(if (== prevRoomNum 140)
			(curRoom setScript: azizaTalk)
		else
			(cSound client: self)
			(curRoom setScript: azizaMoreTalk)
		)
	)
	
	(method (dispose)
		(DisposeScript 35)
		(super dispose:)
	)
	
	(method (cue)
		(cond 
			((!= (cSound number?) 111)
				(cond 
					((or local0 (== (cSound prevSignal?) -1))
						(cSound number: 111 play: self setLoop: -1)
					)
					((== (cSound prevSignal?) 100)
						(= local0 1)
					)
				)
			)
			((and (== (cSound prevSignal?) 20) local1)
				(curRoom newRoom: 120)
			)
		)
	)
)

(instance azizaTalk of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 6))
			(1
				(cSound number: 110 play: hold:)
				(messager say: N_AZIZA V_DOIT C_START 0 self)
			)
			(2
				(globalSound number: 281 play:)
				(aziza setCycle: EndLoop self)
			)
			(3
				(curRoom newRoom: 100)
			)
		)
	)
)

(instance azizaMoreTalk of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 6)
			)
			(1
				(globalSound number: 281 play:)
				(aziza setCycle: EndLoop self)
			)
			(2
				(table setCycle: EndLoop self init:)
			)
			(3
				(= ticks 90)
			)
			(4
				(messager say: N_AZIZA V_DOIT C_AVIS_DEATH 0 self)
			)
			(5
				(globalSound number: 281 play:)
				(aziza setCycle: EndLoop self)
			)
			(6
				(AdAvisFace setCycle: EndLoop self init:)
			)
			(7
				(AdAvisFace loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(8
				(= seconds 4)
			)
			(9
				(AdAvisFace setCycle: BegLoop self)
			)
			(10
				(AdAvisFace
					setLoop: 0
					setCel: (AdAvisFace lastCel:)
					setCycle: BegLoop self
				)
			)
			(11
				(AdAvisFace dispose:)
				(= cycles 3)
			)
			(12
				(messager say: N_AZIZA V_DOIT C_EXPOSITION 0 self)
			)
			(13
				(globalSound number: 281 play:)
				(penta setCycle: EndLoop self init:)
			)
			(14
				(penta loop: 1 cel: 0 setCycle: Forward)
				(= seconds 2)
			)
			(15
				(penta cel: 1)
				(kreeshaFace init:)
				(= seconds 2)
			)
			(16
				(Narrator color: 37)
				(messager say: N_KREESHA V_DOIT C_KREESHA_MESSAGE 0 self)
			)
			(17
				(kreeshaFace dispose:)
				(penta dispose:)
				(= cycles 3)
			)
			(18
				(messager say: N_AZIZA V_DOIT C_GO_TO_TARNA 0 self)
			)
			(19
				(Narrator color: 0)
				(= local1 1)
			)
		)
	)
)

(instance table of Prop
	(properties
		x 151
		y 125
		view 110
	)
)

(instance aziza of Prop
	(properties
		x 68
		y 130
		view 112
		priority 2
		signal fixPriOn
	)
)

(instance penta of Prop
	(properties
		x 149
		y 82
		view 116
		priority 9
		signal (| ignrAct fixPriOn)
	)
)

(instance AdAvisFace of Prop
	(properties
		x 143
		y 121
		view 115
		priority 10
		signal (| ignrAct fixPriOn)
	)
)

(instance kreeshaFace of Prop
	(properties
		x 153
		y 102
		view 118
		priority 10
		signal (| ignrAct fixPriOn)
	)
)

(instance azizaTalker of Talker
	(properties
		x 48
		y 62
		view 112
		loop 2
		talkWidth 135
		color 10
		back 57
		textX 60
	)
	
	(method (init)
		(super init: azizaBust azizaEyes azizaMouth &rest)
	)
)

(instance azizaBust of Prop
	(properties
		view 112
		loop 2
	)
)

(instance azizaMouth of Prop
	(properties
		view 112
		loop 1
		cycleSpeed 10
	)
)

(instance azizaEyes of Prop
	(properties
		view 112
		loop 2
		cycleSpeed 30
	)
)
