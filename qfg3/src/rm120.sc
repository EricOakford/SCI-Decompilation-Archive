;;; Sierra Script 1.0 - (do not remove this comment)
(script# 120)
(include game.sh) (include "120.shm")
(use Main)
(use GloryTalker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Reverse)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm120 0
	sultanTalker 1
)

(instance rm120 of Room
	(properties
		picture 120
		horizon 70
		picAngle 20
	)
	
	(method (init)
		(HandsOff)
		(self setRegions: INTRO)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 0
						319 0
						319 152
						280 117
						251 118
						222 107
						197 107
						180 81
						143 82
						144 101
						135 102
						135 108
						123 130
						80 116
						80 189
					yourself:
				)
		)
		(LoadMany RES_VIEW 122 123 125 960 32)
		(ego
			normalize:
			changeGait: 3
			setScale: Scaler 100 96 170 86
			setHeading: 315
			x: 234
			y: 164
			init:
		)
		(sultan init: stopUpd:)
		(jaafar init: stopUpd:)
		((ScriptID UHURA_TALKER 1)
			view: 968
			loop: 3
			cel: 0
			x: 155
			y: 182
			init:
			stopUpd:
		)
		((ScriptID UHURA_TALKER 0)
			x: 196
			y: 10
			textX: -175
			textY: 3
			talkWidth: 140
		)
		((ScriptID RAKEESH_TALKER 1)
			view: 964
			loop: 2
			cel: 3
			x: 141
			y: 181
			init:
			stopUpd:
		)
		(super init:)
		(self setScript: startUp)
	)
	
	(method (dispose)
		(UnLoad RES_VIEW 122)
		(UnLoad RES_VIEW 123)
		(UnLoad RES_VIEW 125)
		(UnLoad RES_VIEW 960)
		(UnLoad RES_VIEW 32)
		(DisposeScript RAKEESH_TALKER)
		(DisposeScript UHURA_TALKER)
		(super dispose:)
	)
)

(instance startUp of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 3)
			)
			(1
				(cSound number: 120 loop: -1 play:)
				(messager say: N_START V_DOIT C_THREE_DAYS_LATER 0 self)
			)
			(2
				(sultan setCycle: EndLoop self)
			)
			(3
				(messager say: N_SULTAN V_DOIT C_GOODBYE 0 self)
			)
			(4
				(ego setStep: 3 3 yStep: 3 setMotion: MoveTo 165 82 self)
			)
			(5
				(ego setHeading: 315)
				(cond 
					((or (== heroType FIGHTER) (== heroType PALADIN))
						(messager say: N_SULTAN V_DOIT C_SHIELD 0 self)
					)
					((== heroType THIEF)
						(messager say: N_SULTAN V_DOIT C_GRAPNEL 0 self)
					)
					(else
						(messager say: N_SULTAN V_DOIT C_GIFT 0 self)
					)
				)
			)
			(6
				(sultan loop: 3 cel: 0 x: 156 y: 81 setCycle: EndLoop self)
			)
			(7
				(= ticks 60)
			)
			(8
				(sultan setCycle: BegLoop self)
			)
			(9
				(globalSound number: 942 play:)
				(= cycles 10)
			)
			(10
				(sultan
					x: 159
					y: 82
					setPri: 3
					view: 123
					setCel: 0
					setLoop: 0
					setCycle: CycleTo 4 1 self
				)
			)
			(11
				(ego hide:)
				(sultan setCycle: CycleTo 5 1 self)
			)
			(12
				(= ticks 120)
			)
			(13
				(sultan cel: 6)
				(= cycles 5)
			)
			(14
				(ego show:)
				(sultan setCel: 7)
				(= cycles 5)
			)
			(15
				(sultan
					view: 121
					setLoop: 0
					setCel: (sultan lastCel:)
					x: 155
					y: 77
					stopUpd:
				)
				(ego
					setCycle: Reverse
					setLoop: (ego loop?)
					setStep: 2 2
					xStep: 2
					yStep: 2
					setMotion: MoveTo 187 115 self
				)
			)
			(16
				(ego view: 32 setLoop: 1 cel: 0 x: 176 setCycle: EndLoop self)
			)
			(17
				(ego normalize: x: 182 y: 116 setHeading: 315)
				(globalSound number: 932 setLoop: -1 play:)
				(portal
					ignoreActors: TRUE
					cycleSpeed: 10
					setCycle: Forward
					init:
				)
				(= seconds 3)
			)
			(18
				(messager say: N_PORTAL_APPEARS V_DOIT C_PORTAL 0 self)
			)
			(19
				(ego setMotion: PolyPath 90 116 self)
			)
			(20
				(globalSound stop:)
				(curRoom newRoom: 200)
			)
		)
	)
)

(instance portal of Prop
	(properties
		x 61
		y 116
		view 125
	)
)

(instance sultan of Actor
	(properties
		x 155
		y 77
		view 121
		signal ignrAct
	)
)

(instance jaafar of Actor
	(properties
		x 113
		y 97
		view 120
	)
)

(instance sultanTalker of GloryTalker
	(properties
		x 5
		y 15
		view 122
		loop 1
		talkWidth 135
		back 57
		textX 140
		textY 3
	)
	
	(method (init)
		(super init: sultanBust sultanEyes sultanMouth &rest)
	)
)

(instance sultanBust of Prop
	(properties
		nsTop 22
		nsLeft 40
		view 122
		loop 3
	)
)

(instance sultanMouth of Prop
	(properties
		nsTop 51
		nsLeft 41
		view 122
		cycleSpeed 10
	)
)

(instance sultanEyes of Prop
	(properties
		nsTop 40
		nsLeft 43
		view 122
		loop 2
		cycleSpeed 30
	)
)
