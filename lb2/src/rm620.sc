;;; Sierra Script 1.0 - (do not remove this comment)
(script# 620)
(include sci.sh)
(use Main)
(use LBRoom)
(use Inset)
(use Scaler)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm620 0
)

(local
	local0
	local1
	local2
)
(procedure (localproc_14a6 param1)
	(return
		(if (< global129 6)
			(return (- 320 param1))
		else
			(return param1)
		)
	)
)

(instance rm620 of LBRoom
	(properties
		picture 620
	)
	
	(method (init)
		(theIconBar disable: 7)
		(if (timers contains: (ScriptID 90 15))
			((ScriptID 90 15) client: self)
		)
		(ego init: setScale: Scaler 100 100 10 0 normalize: 622)
		(LoadMany 128 621 622)
		(LoadMany 132 611 612 615)
		(if (< global129 6) (= style 16384) (= local2 1))
		(super init:)
		(churning
			init:
			setLoop: (+ (churning loop?) local2)
			x: (localproc_14a6 (churning x?))
			setCycle: Fwd
		)
		(self setRegions: 90)
		(switch global129
			(1
				(= local1 8)
				(animal1 init: setLoop: 7 setCel: 0 posn: 130 130)
				(animal2 init: setLoop: 7 setCel: 1 posn: 144 94)
				(animal3 init: setLoop: 7 setCel: 0 posn: 179 105)
				(animal4 init: setLoop: 7 setCel: 1 posn: 169 149)
			)
			(2
				(= local1 10)
				(animal1 init: setLoop: 7 setCel: 2 posn: 129 103)
				(animal2 init: setLoop: 7 setCel: 3 posn: 167 117)
				(animal3 init: setLoop: 7 setCel: 4 posn: 124 134)
				(animal4 init: setLoop: 7 setCel: 2 posn: 126 166)
				(animal5 init: setLoop: 7 setCel: 4 posn: 162 145)
			)
			(3
				(= local1 9)
				(animal1 init: setLoop: 7 setCel: 5 posn: 137 100)
				(animal2 init: setLoop: 7 setCel: 6 posn: 126 134)
				(animal3 init: setLoop: 7 setCel: 7 posn: 178 151)
			)
			(4
				(= local1 6)
				(animal1 init: setLoop: 7 setCel: 8 posn: 149 96)
			)
			(5
				(= local1 6)
				(animal1 init: setLoop: 7 setCel: 9 posn: 125 133)
				(animal2 init: setLoop: 7 setCel: 10 posn: 142 103)
			)
			(6
				(= local1 8)
				(animal1 init: setLoop: 7 setCel: 13 posn: 142 159)
				(animal2 init: setLoop: 7 setCel: 11 posn: 125 116)
				(animal3 init: setLoop: 7 posn: 173 103)
				(if (and (== currentAct 4) (ego has: 11))
					(animal3 setCel: 15)
				else
					(animal3 setCel: 12)
				)
				(animal4 init: setLoop: 7 setCel: 12 posn: 116 118)
			)
			(7
				(= local1 6)
				(animal1 init: setLoop: 8 setCel: 0 posn: 110 98)
			)
			(8
				(= local1 9)
				(animal1 init: setLoop: 8 setCel: 3 posn: 139 157)
				(animal2 init: setLoop: 8 setCel: 1 posn: 112 102)
				(animal3 init: setLoop: 8 setCel: 2 posn: 164 113)
			)
			(9
				(= local1 10)
				(animal1 init: setLoop: 8 setCel: 5 posn: 114 103)
				(animal2 init: setLoop: 8 setCel: 6 posn: 158 117)
				(animal3 init: setLoop: 8 setCel: 6 posn: 115 141)
				(animal4 init: setLoop: 8 setCel: 5 posn: 129 164)
				(animal5 init: setLoop: 8 setCel: 5 posn: 165 141)
			)
			(10
				(= local1 6)
				(animal1 init: setLoop: 8 setCel: 7 posn: 115 92)
			)
			(11
				(= local1 5)
				(animal1 init: setLoop: 8 setCel: 8 posn: 109 113)
			)
			(12
				(= local1 10)
				(animal1 init: setLoop: 8 setCel: 9 posn: 114 100)
				(animal2 init: setLoop: 8 setCel: 10 posn: 158 117)
				(animal3 init: setLoop: 8 setCel: 10 posn: 109 134)
				(animal4 init: setLoop: 8 setCel: 10 posn: 123 167)
				(animal5 init: setLoop: 8 setCel: 9 posn: 147 145)
			)
			(13
				(= local1 10)
				(animal1 init: setLoop: 0 setCel: 0 posn: 117 125)
				(animal2 init: setLoop: 1 setCel: 0 posn: 134 110)
				(animal3 init: setLoop: 2 setCel: 0 posn: 170 105)
				(animal4 init: setLoop: 3 setCel: 0 posn: 134 148)
				(animal5 init: setLoop: 4 setCel: 0 posn: 173 143)
			)
			(14
				(= local1 6)
				(animal1 init: setLoop: 8 setCel: 11 posn: 163 131)
				(animal2 init: setLoop: 8 setCel: 12 posn: 113 107)
			)
			(else  (= local1 1))
		)
		(self setScript: sSearchVat)
	)
	
	(method (cue)
		((ScriptID 90 15) setReal: self 10 0 0 global125)
	)
	
	(method (newRoom)
		(if (timers contains: (ScriptID 90 15))
			(= timer 0)
			((ScriptID 90 15) client: (ScriptID 90 15))
		)
		(super newRoom: &rest)
	)
)

(instance sSearchVat of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(switch global129
			(1
				(switch local0
					(0
						(animal1 x: (Random 129 131) y: (Random 128 132))
					)
					(2
						(animal2 x: (Random 143 145) y: (Random 92 96))
					)
					(4
						(animal3 x: (Random 178 180) y: (Random 103 107))
					)
					(6
						(animal4 x: (Random 168 170) y: (Random 147 151))
					)
				)
			)
			(2
				(switch local0
					(0
						(animal1 x: (Random 128 130) y: (Random 101 105))
					)
					(2
						(animal2 x: (Random 166 168) y: (Random 115 119))
					)
					(4
						(animal3 x: (Random 123 125) y: (Random 132 136))
					)
					(6
						(animal4 x: (Random 125 127) y: (Random 164 168))
					)
					(8
						(animal5 x: (Random 161 163) y: (Random 143 147))
					)
				)
			)
			(3
				(switch local0
					(0
						(animal1 x: (Random 136 138) y: (Random 98 102))
					)
					(3
						(animal2 x: (Random 125 127) y: (Random 132 136))
					)
					(6
						(animal3 x: (Random 177 179) y: (Random 149 153))
					)
				)
			)
			(4
				(switch local0
					(0
						(animal1 x: (Random 148 150) y: (Random 94 98))
					)
				)
			)
			(5
				(switch local0
					(0
						(animal1 x: (Random 124 126) y: (Random 131 135))
					)
					(3
						(animal2 x: (Random 141 143) y: (Random 101 105))
					)
				)
			)
			(6
				(switch local0
					(0
						(animal1 x: (Random 141 143) y: (Random 157 161))
					)
					(2
						(animal2 x: (Random 124 126) y: (Random 114 118))
					)
					(4
						(animal3 x: (Random 172 174) y: (Random 101 105))
					)
					(6
						(animal4 x: (Random 115 117) y: (Random 116 120))
					)
				)
			)
			(7
				(switch local0
					(0
						(animal1 x: (Random 109 111) y: (Random 96 100))
					)
				)
			)
			(8
				(switch local0
					(0
						(animal1 x: (Random 138 140) y: (Random 155 159))
					)
					(3
						(animal2 x: (Random 111 113) y: (Random 100 104))
					)
					(6
						(animal3 x: (Random 163 165) y: (Random 111 115))
					)
				)
			)
			(9
				(switch local0
					(0
						(animal1 x: (Random 113 115) y: (Random 101 105))
					)
					(2
						(animal2 x: (Random 157 159) y: (Random 115 119))
					)
					(4
						(animal3 x: (Random 114 116) y: (Random 139 143))
					)
					(6
						(animal4 x: (Random 128 130) y: (Random 162 166))
					)
					(8
						(animal5 x: (Random 164 166) y: (Random 139 143))
					)
				)
			)
			(10
				(switch local0
					(0
						(animal1 x: (Random 114 116) y: (Random 90 94))
					)
				)
			)
			(11
				(switch local0
					(0
						(animal1 x: (Random 108 110) y: (Random 111 115))
					)
				)
			)
			(12
				(switch local0
					(0
						(animal1 x: (Random 113 115) y: (Random 98 102))
					)
					(2
						(animal2 x: (Random 157 159) y: (Random 115 119))
					)
					(4
						(animal3 x: (Random 108 110) y: (Random 132 136))
					)
					(6
						(animal4 x: (Random 122 124) y: (Random 165 169))
					)
					(8
						(animal5 x: (Random 146 148) y: (Random 143 147))
					)
				)
			)
			(13
				(switch local0
					(0
						(animal1 x: (Random 116 118) y: (Random 123 127))
					)
					(2
						(animal2 x: (Random 133 135) y: (Random 108 112))
					)
					(4
						(animal3 x: (Random 169 171) y: (Random 103 107))
					)
					(6
						(animal4 x: (Random 133 135) y: (Random 146 150))
					)
					(8
						(animal5 x: (Random 172 174) y: (Random 141 145))
					)
				)
			)
			(14
				(switch local0
					(0
						(animal1 x: (Random 162 164) y: (Random 129 133))
					)
					(3
						(animal2 x: (Random 112 114) y: (Random 105 109))
					)
				)
			)
		)
		(= local0 (mod (++ local0) local1))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setLoop: (- 1 local2)
					setCel: 0
					posn: (localproc_14a6 201) 98
					setPri: 13
					setCycle: End self
				)
			)
			(1
				(ego setLoop: (- 3 local2) setCel: 0 setCycle: End self)
				(sFX number: 611 loop: -1 flags: 1 play:)
			)
			(2
				(ego setLoop: (- 5 local2) setCel: 0 setCycle: End self)
			)
			(3 (ego setCycle: Beg self))
			(4 (= ticks 45))
			(5 (ego setCycle: End self))
			(6
				(sFX stop:)
				(if (Btst 21)
					(curRoom setScript: sFallInVat)
				else
					(= ticks 45)
				)
			)
			(7
				(ego setLoop: (- 11 local2) setCel: 0 setCycle: Fwd)
				(net
					init:
					posn: (if local2 130 else 183) 35
					setLoop: 12
					setMotion:
						MoveTo
						(if local2 (- (net x?) 53) else (net x?))
						(- (net y?) 90)
						self
				)
				(if
					(and
						(== global129 13)
						(not (Btst 22))
						(== currentAct 3)
					)
					(dagger
						init:
						setLoop: 12
						setCel: 1
						setMotion: MoveTo (dagger x?) (- (dagger y?) 90)
					)
				)
			)
			(8
				(net stopUpd:)
				(dagger stopUpd:)
				(ego setCycle: 0)
				(= ticks 60)
			)
			(9
				(if
					(and
						(== global129 13)
						(not (Btst 22))
						(== currentAct 3)
					)
					(curRoom setScript: sFoundDagger)
				else
					(switch global129
						(1 (messager say: 2 0 2))
						(2 (messager say: 3 0 2))
						(3 (messager say: 4 0 2))
						(4 (messager say: 5 0 2))
						(5 (messager say: 6 0 2))
						(6 (messager say: 7 0 2))
						(7 (messager say: 8 0 2))
						(8 (messager say: 9 0 2))
						(9 (messager say: 10 0 2))
						(10 (messager say: 11 0 2))
						(11 (messager say: 12 0 2))
						(12 (messager say: 13 0 2))
						(13
							(if (Btst 155)
								(messager say: 15 0 7)
							else
								(messager say: 15 0 8)
							)
						)
						(14 (messager say: 14 0 2))
					)
					(if (Btst 20) (Bset 21) else (Bset 20))
					(= cycles 1)
				)
			)
			(10
				(theIconBar enable: 7)
				(curRoom newRoom: 610)
				(self dispose:)
			)
		)
	)
)

(instance sFoundDagger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 stop:)
				(sFX number: 616 loop: -1 flags: 1 play:)
				(LoadMany 128 1898 108)
				(messager say: 1 0 1 1 self)
			)
			(1 (messager say: 1 0 1 2 self))
			(2 (messager say: 1 0 1 3 self))
			(3 (messager say: 1 0 1 4 self))
			(4 (messager say: 1 0 1 5 self))
			(5 (messager say: 1 0 1 6 self))
			(6 (messager say: 1 0 1 7 self))
			(7
				(sFX number: 615 loop: 1 flags: 1 play: self)
				(curRoom style: 9 drawPic: 785)
				(curRoom setInset: inDagger)
				(= cycles 10)
			)
			(8 (messager say: 1 0 1 8))
			(9
				(curRoom drawPic: (curRoom picture?))
				(inDagger dispose:)
				(= cycles 5)
			)
			(10
				(dagger dispose:)
				(ego get: 11)
				((ScriptID 21 0) doit: 780)
				(Bset 22)
				(theGame points: 1 155)
				((ScriptID 22 0) doit: 128)
				(= cycles 1)
			)
			(11
				(if (TriggerEvent 8512)
					(curRoom newRoom: 26)
				else
					(if (Btst 20) (Bset 21) else (Bset 20))
					(curRoom newRoom: 610)
				)
				(self dispose:)
			)
		)
	)
)

(instance sFallInVat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 1 0 3)
				(= ticks 45)
			)
			(1
				(ego setLoop: (- 7 local2) setCel: 0 setCycle: End self)
			)
			(2
				(ego setLoop: (- 9 local2) setCel: 0 setCycle: End self)
			)
			(3
				(sFX number: 612 flags: 1 play:)
				(ego
					setPri: 10
					setCycle: 0
					setMotion: MoveTo (localproc_14a6 181) 142 self
				)
			)
			(4
				(bubbles init: setCycle: End self)
			)
			(5
				(bubbles dispose:)
				(= ticks 120)
			)
			(6
				(= deathReason 5)
				(theMusic2 fade:)
				(curRoom newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sSparkle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 180))
			(1 (spark setCycle: End self))
			(2 (self dispose:))
		)
	)
)

(instance net of Actor
	(properties
		x 183
		y 35
		view 622
		loop 12
		priority 11
		signal $6011
		moveSpeed 3
	)
)

(instance dagger of Actor
	(properties
		x 188
		y 158
		view 622
		loop 12
		cel 1
		priority 11
		signal $4011
		moveSpeed 3
	)
)

(instance churning of Prop
	(properties
		x 112
		y 51
		view 621
		loop 10
		priority 10
		signal $4011
		cycleSpeed 12
	)
)

(instance bubbles of Prop
	(properties
		x 132
		y 60
		view 621
		loop 6
		priority 11
		signal $4011
		cycleSpeed 11
	)
)

(instance spark of Prop
	(properties
		x 151
		y 104
		view 108
		priority 15
		signal $0010
	)
)

(instance animal1 of View
	(properties
		view 621
		priority 13
		signal $4010
	)
)

(instance animal2 of View
	(properties
		view 621
		priority 13
		signal $4010
	)
)

(instance animal3 of View
	(properties
		view 621
		priority 13
		signal $4010
	)
)

(instance animal4 of View
	(properties
		view 621
		priority 13
		signal $4010
	)
)

(instance animal5 of View
	(properties
		view 621
		priority 13
		signal $4010
	)
)

(instance inDagger of Inset
	(properties
		view 1898
		loop 1
		x 98
		y 3
		hideTheCast 1
	)
	
	(method (init)
		(super init: &rest)
		(spark init:)
		(self setScript: sSparkle)
	)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)
