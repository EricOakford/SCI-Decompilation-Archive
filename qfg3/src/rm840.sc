;;; Sierra Script 1.0 - (do not remove this comment)
(script# 840)
(include sci.sh)
(use Main)
(use Polygon)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm840 0
)

(local
	local0
)
(instance rm840 of Rm
	(properties
		picture 840
	)
	
	(method (init)
		(if (Btst 77)
			(ego setScale: x: 160 y: 137 init: normalize:)
		else
			(ego setScale: x: 160 y: 189 init: normalize:)
		)
		(super init:)
		(cSound number: 840 setLoop: -1 play: self)
		(if (Btst 77)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init: 48 83 129 83 143 143 167 164 167 173 132 189 32 189 32 83 50 83
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 162 189 195 158 195 83 319 83 319 189
						yourself:
					)
			)
		)
		(if (Btst 77)
			(curRoom setScript: secondEntrance)
		else
			(Bset 77)
			(curRoom setScript: firstEntrance)
		)
	)
	
	(method (dispose)
		(LoadMany 0 41 36 39)
		(super dispose:)
	)
	
	(method (cue)
		(if local0 (firstEntrance cue:) (= local0 0))
	)
)

(instance firstEntrance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: 2 6 4 0 self)
			)
			(1
				(if (Btst 150)
					(messager say: 2 6 5 0 self)
				else
					(self cue:)
				)
			)
			(2
				(ego solvePuzzle: 339 3 setMotion: MoveTo 158 155 self)
			)
			(3
				((ScriptID 36 1)
					x: 160
					y: 200
					ignoreActors: 1
					setScale:
					init:
					setCycle: Walk
					setMotion: MoveTo 72 174 self
				)
				((ScriptID 39 1)
					x: 160
					y: 200
					ignoreActors: 1
					setScale:
					init:
					setStep: 3 2
					moveSpeed: 12
					setCycle: Walk
					setMotion: MoveTo 198 161
				)
			)
			(4
				((ScriptID 41 1)
					x: 160
					y: 200
					ignoreActors: 1
					setScale:
					init:
					setCycle: Walk
					setMotion: MoveTo 118 149
				)
				(reesha
					init:
					setCycle: Walk
					setMotion: MoveTo 250 168 self
				)
			)
			(5 (= cycles 1))
			(6 (= local0 1))
			(7
				(johariBiz init:)
				(curRoom drawPic: (curRoom picture?) 9)
				(= cycles 2)
			)
			(8 (= local0 1))
			(9
				(manuBiz init:)
				(curRoom drawPic: (curRoom picture?) 9)
				(= cycles 2)
			)
			(10 (= local0 1))
			(11
				(egoBiz init:)
				(curRoom drawPic: (curRoom picture?) 9)
				(= cycles 2)
			)
			(12 (= local0 1))
			(13
				(yesufuBiz init:)
				(curRoom drawPic: (curRoom picture?) 9)
				(= cycles 2)
			)
			(14 (= local0 1))
			(15
				(reeshaBiz init:)
				(curRoom drawPic: (curRoom picture?) 9)
				(= seconds 3)
			)
			(16 (= local0 1))
			(17
				(johariBiz setCycle: End self)
			)
			(18 (= local0 1))
			(19
				(manuBiz setCycle: End self)
			)
			(20 (= local0 1))
			(21 (egoBiz setCycle: End self))
			(22 (= local0 1))
			(23
				(yesufuBiz setCycle: End self)
			)
			(24 (= local0 1))
			(25
				(reeshaBiz setCycle: End self)
			)
			(26 (= seconds 2))
			(27
				(curRoom newRoom: 549)
				(self dispose:)
			)
		)
	)
)

(instance secondEntrance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(johariBiz view: 842 loop: 1 cel: 1 x: 37 y: 125 init:)
				(yesufuBiz view: 842 loop: 0 cel: 2 x: 159 y: 81 init:)
				(manuBiz view: 842 loop: 1 cel: 0 x: 83 y: 91 init:)
				(reeshaBiz view: 842 loop: 0 cel: 0 x: 149 y: 136 init:)
				(= seconds 3)
			)
			(1
				(if (not (Btst 117))
					(if 12
						(= [egoStats 18] (ego maxMana:))
						(= [egoStats 16] (ego maxHealth:))
						(messager say: 2 6 3 0 self)
					else
						(= [egoStats 16] (ego maxHealth:))
						(messager say: 2 6 2 0 self)
					)
				else
					(self cue:)
				)
			)
			(2
				(switch heroType
					(0 (curRoom newRoom: 851))
					(3 (curRoom newRoom: 852))
					(1 (curRoom newRoom: 853))
					(2 (curRoom newRoom: 854))
				)
			)
		)
	)
)

(instance egoBiz of Prop
	(properties
		x 155
		y 111
		view 841
		signal $4000
	)
)

(instance johariBiz of Prop
	(properties
		x 29
		y 129
		view 841
		loop 3
		signal $4000
	)
)

(instance yesufuBiz of Prop
	(properties
		x 228
		y 116
		view 841
		loop 1
		signal $4000
	)
)

(instance manuBiz of Prop
	(properties
		x 84
		y 123
		view 841
		loop 4
		signal $4000
	)
)

(instance reesha of Actor
	(properties
		x 160
		y 200
		view 831
		cel 2
		signal $4000
	)
)

(instance reeshaBiz of Prop
	(properties
		x 278
		y 134
		view 841
		loop 2
		signal $4000
	)
)
