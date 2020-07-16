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
	local0
	local1
	local2 =  114
	local3 =  26
	local4
	local5
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
	
	(method (doit &tmp temp0 temp1)
		(++ local5)
		(= temp0 3)
		(if local4 (Palette 6 241 255 -1))
		(cond 
			((== (mod local5 10) 0)
				(Palette 6 216 218 temp0)
				(Palette 6 233 235 temp0)
				(Palette 6 225 227 temp0)
			)
			((== (mod local5 10) 2)
				(Palette 6 217 219 temp0)
				(Palette 6 234 236 temp0)
				(Palette 6 227 229 temp0)
			)
			((== (mod local5 10) 4)
				(Palette 6 218 220 temp0)
				(Palette 6 235 237 temp0)
				(Palette 6 228 230 temp0)
			)
			((== (mod local5 10) 6)
				(Palette 6 219 221 temp0)
				(Palette 6 236 238 temp0)
				(Palette 6 229 231 temp0)
			)
			((== (mod local5 10) 8)
				(Palette 6 220 222 temp0)
				(Palette 6 237 239 temp0)
				(Palette 6 230 232 temp0)
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(forestSound vol: 117 init: play:)
				(Graph GDrawLine 63 67 0 130 local2 -1 -1)
				(Graph GDrawLine 63 66 0 129 local3 -1 -1)
				(Graph GDrawLine 71 68 189 127 local2 -1 -1)
				(Graph GDrawLine 71 67 189 126 local3 -1 -1)
				(= cycles 2)
			)
			(1 (= cycles 15))
			(2
				(if cDAudio (robin init: play:))
				(Display 95 0
					p_at 128 26
					p_time 4
					p_font SYSFONT
					p_color 0
				)
				(Display 95 1
					p_at 104 36
					p_time 4
					p_font SYSFONT
					p_color 0
				)
				(Display 95 0
					p_at 127 25
					p_time 4
					p_font SYSFONT
					p_color 30
				)
				(Display 95 1
					p_at 103 35
					p_time 4
					p_font SYSFONT
					p_color 30
				)
				(= cycles 18)
			)
			(3 (= cycles 4))
			(4
				(= global126 1)
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
				(= global126 1)
				(curRoom drawPic: 95)
				(grin init:)
				(Graph GDrawLine 0 142 38 142 local2 -1 -1)
				(Graph GDrawLine 0 141 38 141 local3 -1 -1)
				(Graph GDrawLine 47 142 65 142 local2 -1 -1)
				(Graph GDrawLine 46 141 66 141 local3 -1 -1)
				(Graph GDrawLine 72 142 78 142 local2 -1 -1)
				(Graph GDrawLine 73 141 86 141 local3 -1 -1)
				(Graph GDrawLine 82 142 85 142 local2 -1 -1)
				(Graph GDrawLine 107 142 131 142 local2 -1 -1)
				(Graph GDrawLine 107 141 160 141 local3 -1 -1)
				(Graph GDrawLine 148 142 160 142 local2 -1 -1)
				(Graph GDrawLine 168 142 189 142 local2 -1 -1)
				(Graph GDrawLine 168 141 189 141 local3 -1 -1)
				(curRoom overlay: 96)
				(= cycles 3)
			)
			(9
				(= global126 0)
				(introSound init: play:)
				(forestSound dispose:)
				(= local4 1)
				(= cycles 5)
			)
			(10 (= seconds 10))
			(11
				(curRoom newRoom: 220)
				(self dispose:)
			)
		)
	)
)

(instance bowString of Script
	(properties)
	
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
				(= global126 0)
				(hand dispose:)
				(arrow dispose:)
				(= local0 (Graph GSaveBits 0 132 189 141 1))
				(= local1 (Graph GSaveBits 0 143 189 151 1))
				(Graph GDrawLine 30 139 159 139 local2 -1 -1)
				(Graph GDrawLine 70 136 119 136 local2 -1 -1)
				(Graph GDrawLine 90 133 99 133 local2 -1 -1)
				(Graph GDrawLine 30 144 159 144 local3 -1 -1)
				(Graph GDrawLine 70 147 119 147 local3 -1 -1)
				(Graph GDrawLine 90 150 99 150 local3 -1 -1)
				(Graph GDrawLine 0 142 189 142 local2 1 -1)
				(Graph GDrawLine 0 141 189 141 local3 1 -1)
				(Graph GReAnimate 0 64 189 148)
				(= cycles 1)
			)
			(2
				(thwip init: play:)
				(Graph GRestoreBits local0)
				(Graph GRestoreBits local1)
				(Graph GReAnimate 0 132 189 141)
				(Graph GReAnimate 0 143 189 151)
				(= cycles 1)
			)
			(3
				(= local0 (Graph GSaveBits 0 132 189 141 1))
				(= local1 (Graph GSaveBits 0 143 189 151 1))
				(Graph GDrawLine 30 139 159 139 local3 -1 -1)
				(Graph GDrawLine 30 144 159 144 local2 -1 -1)
				(Graph GReAnimate 0 64 189 148)
				(= cycles 1)
			)
			(4
				(Graph GRestoreBits local0)
				(Graph GRestoreBits local1)
				(Graph GReAnimate 0 132 189 141)
				(Graph GReAnimate 0 143 189 151)
				(= cycles 1)
			)
			(5 (self dispose:))
		)
	)
)
