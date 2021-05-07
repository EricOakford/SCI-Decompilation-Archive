;;; Sierra Script 1.0 - (do not remove this comment)
(script# 777)
(include game.sh)
(use Main)
(use Path)
(use Sound)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	myWill 0
)

(local
	penPts = [
		204 154
		204 159
		211 162
		210 152
		218 159
		215 152
		223 160
		217 151
		221 160
		224 155
		228 155
		229 152
		234 153
		232 150
		240 154
		238 144
		247 154
		244 143
		252 145
		251 150
		255 145
		258 147
		259 144
		263 152
		260 149
		262 141
		265 145
		266 141
		272 144
		340 144
		PATHEND
	]
	bloodCel
	local62
)
(instance squarePath of Path
	(properties)
	
	(method (at n)
		(return [penPts n])
	)
)

(instance myWill of Room
	(properties
		picture 77
		style DISSOLVE
	)
	
	(method (init)
		(super init:)
		(if menuBarInitialized
			(TheMenuBar hide:)
		)
		(HandsOff)
		(cSound stop:)
		(LoadMany SOUND 20 130)
		(myMusic number: 20)
		(Blood setPri: 3 init:)
		(Drip1 setPri: 3 init: hide:)
		(Drip2 setPri: 3 init: hide:)
		(Drip3 setPri: 3 init: hide:)
		(signature setPri: 1 init: hide:)
		(Hand setLoop: 1 init:)
		(knife setPri: 2 init: hide:)
		(Pool init: hide:)
		(Glop1 init: hide:)
		(Glop2 init: hide:)
		(self setScript: myStab)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript PATH)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(cond 
					(
						(or
							(== (event message?) `S)
							(== (event message?) `s)
						)
						(event claimed: TRUE)
						(curRoom newRoom: 778)
					)
					(
						(or
							(== (event message?) ENTER)
							(== (event message?) SPACEBAR)
						)
						(Bset fSkippedIntro)
					)
				)
			)
			(mouseDown
				(Bset fSkippedIntro)
			)
		)
		(if (Btst fSkippedIntro)
			(event claimed: TRUE)
			(curRoom newRoom: 44)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance myStab of Script
	
	(method (doit)
		(super doit:)
		(switch bloodCel
			(3
				(if (not (Drip2 cycler?))
					(Drip2 show: setCycle: Forward)
				)
			)
			(5
				(if (not (Drip1 cycler?))
					(Drip1 show: cycleSpeed: 1 setCycle: Forward)
					(Drip3 show: setCycle: Forward)
				)
			)
		)
		(if (and (== (myMusic prevSignal?) 10) (== state 5))
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(myMusic loop: 1 play:)
				(= cycles 7)
			)
			(1
				(Hand setMotion: MoveTo 203 153 self)
			)
			(2
				(if howFast
					(Hand setMotion: squarePath self)
					(signature show: cycleSpeed: 5 setCycle: EndLoop)
				else
					(Hand setMotion: MoveTo 340 144 self)
					(signature show: cycleSpeed: 0 setCycle: EndLoop)
				)
			)
			(3
				(Hand setMotion: MoveTo 392 189 self)
			)
			(4
				(if (!= (myMusic prevSignal?) -1)
					(= state 3)
				)
				(= cycles 1)
			)
			(5
				(myMusic prevSignal: 100 number: 130 loop: 1 play:)
			)
			(6
				(knife show: stopUpd:)
				(= cycles 1)
			)
			(7
				(ShakeScreen 5 (| shakeSRight shakeSDiagonal))
				(= seconds 2)
			)
			(8
				(Pool show: cycleSpeed: 1 setCycle: EndLoop)
				(= cycles 7)
			)
			(9
				(if (== (myMusic prevSignal?) -1)
					(= state 10)
					(= cycles 1)
				)
				(Glop1 show: setCycle: EndLoop self)
				(Glop2 show: setCycle: EndLoop)
				(if (== bloodCel 0)
					(addToPics add: Title eachElementDo: #init doit:)
				)
			)
			(10
				(Glop2 cel: 0)
				(cond 
					(howFast
						(if (< bloodCel 9)
							(++ bloodCel)
						else
							(= bloodCel 9)
						)
					)
					((< bloodCel 7)
						(++ bloodCel)
						(++ bloodCel)
						(++ bloodCel)
					)
					(else
						(= bloodCel 9)
					)
				)
				(Blood cel: bloodCel)
				(if (== (myMusic prevSignal?) -1)
					(= cycles 1)
				else
					(= state 8)
					(= cycles 7)
				)
			)
			(11
				(Blood cel: 9 stopUpd:)
				(Glop1 hide:)
				(Glop2 hide:)
				(Drip1 setCycle: EndLoop)
				(Drip2 setCycle: EndLoop)
				(Drip3 setCycle: EndLoop self)
			)
			(12
				(client setScript: 0)
				(curRoom newRoom: 778)
			)
		)
	)
)

(instance Title of PicView
	(properties
		y 97
		x 187
		view 184
		priority 3
	)
)

(instance knife of Prop
	(properties
		y 71
		x 177
		view 277
	)
)

(instance signature of Prop
	(properties
		y 162
		x 203
		view 177
	)
)

(instance Blood of Prop
	(properties
		y 97
		x 187
		view 184
		loop 1
	)
)

(instance Drip1 of Prop
	(properties
		y 156
		x 93
		view 926
	)
)

(instance Drip2 of Prop
	(properties
		y 150
		x 176
		view 926
	)
)

(instance Drip3 of Prop
	(properties
		y 165
		x 278
		view 926
	)
)

(instance Glop1 of Prop
	(properties
		y 47
		x 168
		view 277
		loop 2
	)
)

(instance Glop2 of Prop
	(properties
		y 20
		x 203
		view 277
		loop 3
	)
)

(instance Pool of Prop
	(properties
		y 69
		x 133
		view 277
		loop 1
	)
)

(instance Hand of Actor
	(properties
		y 253
		x 204
		view 177
	)
)

(instance myMusic of Sound)
