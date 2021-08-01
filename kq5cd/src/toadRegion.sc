;;; Sierra Script 1.0 - (do not remove this comment)
(script# 551)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	toadRegion 0
	toad 1
	hopAway 2
	hopAbout 3
)

(local
	hopTimer
	i
	toadXY = [
		136 137
		188 152
		30 129
		232 143
		302 75
		257 130
		45 156
		253 142
		24 58
		116 183
		164 136
		208 156
		300 121
		123 123
		303 118
		60 177
		124 134
		258 89
		127 115
		244 181
		78 139
		122 159
		18 136
		275 225
		301 117
		180 112
		163 107
		20 154
		307 177
		16 178
		132 157
		172 170
		62 150
		217 146
		26 159
		33 130
		213 151
		65 155
		267 164
		216 205
		88 122
		150 138
		34 121
		80 205
		168 156
		47 137
		232 152
		296 79
		288 143
		55 127
		174 140
		223 160
		72 145
		330 123
		189 169
		72 140
		215 84
		-10 120
		214 89
		42 83
		217 140
		236 151
		84 186
		330 157
		151 136
		320 156
		12 165
		330 179
		158 87
		330 73
		]
)
(instance toadRegion of Region
	
	(method (init)
		(super init: &rest)
		(= i
			(switch curRoomNum
				(19 0)
				(20 20)
				(21 40)
				(22 60)
				(24 80)
				(25 100)
				(26 120)
			)
		)
		(toad
			init:
			loop: (Random 0 1)
			x:
				(switch (Random 0 1)
					(0 [toadXY i])
					(1 [toadXY (+ i 2)])
				)
			y:
				(switch (Random 0 1)
					(0 [toadXY (+ i 1)])
					(1 [toadXY (+ i 3)])
				)
		)
		(if (== curRoomNum 22)
			(toad view: 461 setStep: 1 1)
		)
	)
	
	(method (newRoom n)
		(= initialized FALSE)
		(toad setScript: 0 dispose:)
		(super newRoom: n &rest)
	)
)

(instance toad of Actor
	(properties
		view 459
		signal (| ignrAct ignrHrz)
		illegalBits $0000
	)
	
	(method (doit)
		(super doit:)
		(if (== cel 1)
			(cond 
				((!= curRoomNum 22)
					(theMusic4 number: (Random 99 100) loop: 1 play:)
				)
				(
					(or
						(== script hopAbout)
						(== ((inventory at: iBottle) owner?) 200)
					)
					(theMusic4 number: (Random 99 100) loop: 1 play: 40)
				)
			)
		)
		(if (self script?)
			0
		else
			(++ hopTimer)
			(if (or (< (self distanceTo: ego) 35) (> hopTimer 60))
				(self setScript: hopAway)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) userEvent))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 837)
					(event claimed: TRUE)
				)
				(verbDo
					(SpeakAudio 338)
					(event claimed: TRUE)
				)
				(verbTalk
					(SpeakAudio 9117)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance hopAway of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(or
						(== prevRoomNum (curRoom south?))
						(== prevRoomNum (curRoom east?))
					)
					(toad
						setCycle: Walk
						setMotion: MoveTo [toadXY (+ i 4)] [toadXY (+ i 5)] self
					)
				else
					(toad
						setCycle: Walk
						setMotion: MoveTo [toadXY (+ i 6)] [toadXY (+ i 7)] self
					)
				)
			)
			(1
				(toad
					hide:
					stopUpd:
					setCel: 0
					setScript: (if (> (theGame detailLevel:) 1) hopAbout else 0)
				)
			)
		)
	)
)

(instance hopAbout of Script
	(properties
		register 8
	)
	
	(method (doit)
		(super doit:)
		(if (and (not script) (== state 1))
			(self cue:)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= seconds (Random 2 4)))
			(1 0)
			(2
				(if (== register 20) (= register 8))
				(= temp0 (* (Random 0 1) 2))
				(toad
					show:
					setCycle: Walk
					x: [toadXY (+ i temp0 register)]
					y: [toadXY (+ i 1 temp0 register)]
					setMotion:
						MoveTo
						[toadXY (- (+ i 2 register) temp0)]
						[toadXY (- (+ i 3 register) temp0)]
						self
				)
			)
			(3
				(toad hide: setCel: 0)
				(+= register 4)
				(self init:)
			)
		)
	)
)
