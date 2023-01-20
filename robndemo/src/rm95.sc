;;; Sierra Script 1.0 - (do not remove this comment)
(script# 95)
(include game.sh)
(use Main)
(use Sound)
(use Game)
(use Actor)
(use System)

(public
	rm95 0
)

(local
	saveBits
	saveBits2
	brownLineColor =  114
	goldLineColor =  26
	paletteCued
	roomCycles
	[local6 2]
)
(instance rm95 of Room
	(properties
		picture 95
	)
	
	(method (init)
		(super init:)
		(InitAddToPics bowPiece)
		(hand setPri: 13 init:)
		(grin setPri: 13 init:)
		(arrow setPri: 13 init:)
		(self setScript: countDown)
	)
	
	(method (doit &tmp palBlue temp1)
		(++ roomCycles)
		(= palBlue 3)
		(if paletteCued
			(Palette PALCycle 241 255 -1))
		(cond 
			((== (mod roomCycles 10) 0)
				(Palette 6 216 218 palBlue)
				(Palette 6 233 235 palBlue)
				(Palette 6 225 227 palBlue)
			)
			((== (mod roomCycles 10) 2)
				(Palette 6 217 219 palBlue)
				(Palette 6 234 236 palBlue)
				(Palette 6 227 229 palBlue)
			)
			((== (mod roomCycles 10) 4)
				(Palette 6 218 220 palBlue)
				(Palette 6 235 237 palBlue)
				(Palette 6 228 230 palBlue)
			)
			((== (mod roomCycles 10) 6)
				(Palette 6 219 221 palBlue)
				(Palette 6 236 238 palBlue)
				(Palette 6 229 231 palBlue)
			)
			((== (mod roomCycles 10) 8)
				(Palette 6 220 222 palBlue)
				(Palette 6 237 239 palBlue)
				(Palette 6 230 232 palBlue)
			)
		)
		(super doit:)
	)
)

(instance robin of Sound
	(properties
		number 501
		priority 15
	)
)

(instance bowPiece of PicView
	(properties
		x 216
		y 100
		view 95
		loop 3
		priority 15
		signal ignrAct
	)
)

(instance hand of Prop
	(properties
		x 44
		y 63
		view 95
		signal ignrAct
	)
)

(instance grin of Prop
	(properties
		x 99
		y 77
		view 95
		loop 1
		signal ignrAct
	)
)

(instance arrow of Actor
	(properties
		x 69
		y 67
		view 95
		loop 2
		signal ignrAct
	)
)

(instance thwip of Sound
	(properties
		number 100
		priority 14
	)
)

(instance thunk of Sound
	(properties
		number 101
		priority 14
	)
)

(instance countDown of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(forestSound vol: 117 init: play:)
				(Graph GDrawLine 63 67 0 130 brownLineColor -1 -1)
				(Graph GDrawLine 63 66 0 129 goldLineColor -1 -1)
				(Graph GDrawLine 71 68 189 127 brownLineColor -1 -1)
				(Graph GDrawLine 71 67 189 126 goldLineColor -1 -1)
				(= cycles 2)
			)
			(1
				(= cycles 15)
			)
			(2
				(if numDACs
					(robin init: play:)
				)
				(Display 95 0
					p_at 128 26
					p_time 4
					p_font 0
					p_color 0
				)
				(Display 95 1
					p_at 104 36
					p_time 4
					p_font 0
					p_color 0
				)
				(Display 95 0
					p_at 127 25
					p_time 4
					p_font 0
					p_color 30
				)
				(Display 95 1
					p_at 103 35
					p_time 4
					p_font 0
					p_color 30
				)
				(= cycles 18)
			)
			(3
				(= cycles 4)
			)
			(4
				(= talkerOnScreen TRUE)
				(self setScript: bowString self)
			)
			(5
				(forestSound fade: 0 5 5 1)
				(= cycles 5)
			)
			(6
				(thunk init: play:)
				(= cycles 5)
			)
			(7
				(grin setCel: 1)
				(= cycles 4)
			)
			(8
				(= talkerOnScreen TRUE)
				(curRoom drawPic: 95)
				(grin init:)
				(Graph GDrawLine 0 142 38 142 brownLineColor -1 -1)
				(Graph GDrawLine 0 141 38 141 goldLineColor -1 -1)
				(Graph GDrawLine 47 142 65 142 brownLineColor -1 -1)
				(Graph GDrawLine 46 141 66 141 goldLineColor -1 -1)
				(Graph GDrawLine 72 142 78 142 brownLineColor -1 -1)
				(Graph GDrawLine 73 141 86 141 goldLineColor -1 -1)
				(Graph GDrawLine 82 142 85 142 brownLineColor -1 -1)
				(Graph GDrawLine 107 142 131 142 brownLineColor -1 -1)
				(Graph GDrawLine 107 141 160 141 goldLineColor -1 -1)
				(Graph GDrawLine 148 142 160 142 brownLineColor -1 -1)
				(Graph GDrawLine 168 142 189 142 brownLineColor -1 -1)
				(Graph GDrawLine 168 141 189 141 goldLineColor -1 -1)
				(curRoom overlay: 96)
				(= cycles 3)
			)
			(9
				(= talkerOnScreen FALSE)
				(introSound init: play:)
				(forestSound dispose:)
				(= paletteCued TRUE)
				(= cycles 5)
			)
			(10
				(= seconds 10)
			)
			(11
				(curRoom newRoom: 220)
				(self dispose:)
			)
		)
	)
)

(instance bowString of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom drawPic: 95)
				(InitAddToPics bowPiece)
				(arrow posn: 183 84 init:)
				(hand init: setCel: 1)
				(grin init:)
				(= cycles 2)
			)
			(1
				(= talkerOnScreen FALSE)
				(hand dispose:)
				(arrow dispose:)
				(= saveBits (Graph GSaveBits 0 132 189 141 1))
				(= saveBits2 (Graph GSaveBits 0 143 189 151 1))
				(Graph GDrawLine 30 139 159 139 brownLineColor -1 -1)
				(Graph GDrawLine 70 136 119 136 brownLineColor -1 -1)
				(Graph GDrawLine 90 133 99 133 brownLineColor -1 -1)
				(Graph GDrawLine 30 144 159 144 goldLineColor -1 -1)
				(Graph GDrawLine 70 147 119 147 goldLineColor -1 -1)
				(Graph GDrawLine 90 150 99 150 goldLineColor -1 -1)
				(Graph GDrawLine 0 142 189 142 brownLineColor 1 -1)
				(Graph GDrawLine 0 141 189 141 goldLineColor 1 -1)
				(Graph GReAnimate 0 64 189 148)
				(= cycles 1)
			)
			(2
				(thwip init: play:)
				(Graph GRestoreBits saveBits)
				(Graph GRestoreBits saveBits2)
				(Graph GReAnimate 0 132 189 141)
				(Graph GReAnimate 0 143 189 151)
				(= cycles 1)
			)
			(3
				(= saveBits (Graph GSaveBits 0 132 189 141 1))
				(= saveBits2 (Graph GSaveBits 0 143 189 151 1))
				(Graph GDrawLine 30 139 159 139 goldLineColor -1 -1)
				(Graph GDrawLine 30 144 159 144 brownLineColor -1 -1)
				(Graph GReAnimate 0 64 189 148)
				(= cycles 1)
			)
			(4
				(Graph GRestoreBits saveBits)
				(Graph GRestoreBits saveBits2)
				(Graph GReAnimate 0 132 189 141)
				(Graph GReAnimate 0 143 189 151)
				(= cycles 1)
			)
			(5
				(self dispose:)
			)
		)
	)
)
