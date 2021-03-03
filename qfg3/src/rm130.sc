;;; Sierra Script 1.0 - (do not remove this comment)
(script# 130)
(include game.sh)
(use Main)
(use GloryWindow)
(use IconBar)
(use LoadMany)
(use GControl)
(use Window)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm130 0
)

(local
	[local0 2]
	buttonPressed
)
(procedure (localproc_08e7 param1)
	(param1 init: show:)
	(if (not buttonPressed)
		(DrawPic 130 PIXELDISSOLVE)
		(Animate (cast elements?) FALSE)
		(if (== param1 startPanel)
			(localproc_08e7 playPanel)
		else
			(localproc_08e7 startPanel)
		)
	)
)

(procedure (SpeedTest &tmp temp0 temp1 temp2 temp3)
	(= temp0 (GetTime))
	(= temp2 5000)
	(while temp2
		(= temp3 102)
		(-- temp2)
	)
	(= temp2 5000)
	(while temp2
		(Animate)
		(-- temp2)
	)
	(= temp1 (GetTime))
	(= temp3
		(switch (/ (Abs (- temp1 temp0)) 10)
			(0 3)
			(1 2)
			(2 1)
			(else  0)
		)
	)
)

(instance rm130 of Room
	(properties
		picture 1
	)
	
	(method (init)
		(HandsOff)
		(SetCursor 0)
		(User canInput: TRUE)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(theIconBar disable:)
		(SetPort 0 0 200 320 0 0)
		(super init:)
		(if (OneOf prevRoomNum 140 54 102 63)
			(III setLoop: 4 setCel: 6 x: 85 y: 121 init:)
			(wagesSign setLoop: 0 setCel: 8 init:)
			(SetPort 0 0 190 320 10 0)
			(DrawPic 130 HSHUTTER)
			(cSound changeTo: 2)
			(seeMeGo start: 16)
		)
		(self setScript: seeMeGo)
		(= machineSpeed (SpeedTest))
	)
	
	(method (dispose)
		(startPanel dispose:)
		(playPanel dispose:)
		(theIconBar enable:)
		(LoadMany FALSE FORCOUNT GCONTROL)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((OneOf (event type?) keyDown mouseDown joyDown)
				(if (< (seeMeGo state?) 4)
					(cSound client: 0)
					(globalSound client: 0)
					(seeMeGo seconds: 0 cycles: 0 ticks: 0 changeState: 4)
				else
					(cSound client: 0)
					(globalSound client: 0)
					(seeMeGo seconds: 0 cycles: 0 ticks: 0 changeState: 18)
				)
				(event claimed: TRUE)
			)
			((== (event type?) mouseUp)
				(event claimed: TRUE)
			)
		)
		(super handleEvent: event &rest)
	)
)

(instance seeMeGo of Script

	(method (doit)
		(if (< (self state?) 4)
			(Palette PALCycle 95 218 -1)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound number: 1 play: self)
			)
			(1
				(glint init: setCycle: EndLoop)
			)
			(2
				(glint2 init: setCycle: CycleTo 6 1)
			)
			(3
				(glint2 setCycle: EndLoop)
			)
			(4
				(SetPort 0 0 190 320 10 0)
				(DrawPic 130 IRISIN)
				(glint dispose:)
				(glint2 dispose:)
				(gloryDude cel: 0 init:)
				(cSound number: 2 play:)
				(= seconds 2)
			)
			(5
				(gloryDude setCycle: EndLoop self)
			)
			(6
				(globalSound number: 7 play: self)
				(= ticks 15)
			)
			(7
				(lFx init: setCycle: Forward)
			)
			(8
				(lFx dispose:)
				(globalSound number: 8 play: self)
				(sFx init: setPri: 14 setCycle: Forward)
			)
			(9
				(sFx setLoop: 2 cel: 0 x: 206 y: 55 setCycle: EndLoop self)
				(globalSound number: 11 play:)
			)
			(10
				(sFx dispose:)
				(beam init: setPri: 14 setCycle: EndLoop self)
			)
			(11
				(globalSound number: 9 play:)
				(beam dispose:)
				(III init: setCycle: EndLoop self)
			)
			(12
				(III setCycle: BegLoop self)
			)
			(13
				(III x: 85 y: 121 setLoop: 4 cel: 0 setCycle: CycleTo 1 1 self)
			)
			(14
				(Palette PALIntensity 0 255 400)
				(III setCycle: EndLoop self)
				(Palette PALIntensity 0 255 100)
			)
			(15
				(wagesSign init: cycleSpeed: 5 setCycle: CycleTo 3 1 self)
			)
			(16
				(globalSound number: 131 play:)
				(wagesSign setCycle: EndLoop self)
			)
			(17 (= ticks 180))
			(18
				(cSound hold:)
				(gloryDude dispose:)
				(wagesSign addToPic:)
				(III addToPic:)
				(beam dispose:)
				(sFx dispose:)
				(lFx dispose:)
				(= cycles 3)
			)
			(19
				(Palette PALIntensity 72 255 60)
				(SetCursor 1)
				(theGame setCursor: normalCursor TRUE 160 90)
				(localproc_08e7 startPanel)
			)
		)
	)
)


(instance III of Prop
	(properties
		x 54
		y 100
		view 134
		loop 5
	)
)

(instance gloryDude of Prop
	(properties
		x 201
		y 135
		view 131
		loop 2
	)
)

(instance wagesSign of Prop
	(properties
		x 260
		y 175
		view 135
		cel 1
	)
)

(instance lFx of Prop
	(properties
		x 204
		y 29
		view 134
	)
)

(instance sFx of Prop
	(properties
		x 203
		y 57
		view 134
		loop 1
	)
)

(instance beam of Prop
	(properties
		x 164
		y 72
		view 134
		loop 3
	)
)

(instance glint of Prop
	(properties
		x 134
		y 34
		view 133
		loop 1
		cycleSpeed 8
	)
)

(instance glint2 of Prop
	(properties
		x 60
		y 155
		view 133
		cycleSpeed 8
	)
)

(instance startWin of Window
	(properties
		top 32
		left 61
		bottom 160
		right 258
		type $0083
	)
	
	(method (open &tmp i)
		(super open: &rest)
		(Graph GFillRect 10 10 108 176 1 57 -1)
		(Graph GShowBits 9 9 109 175 1)
		(DrawCel 132 8 0 1 1 -1)
		(DrawCel 132 9 0 1 107 -1)
		(for ((= i 0)) (<= i 8) ((++ i))
			(DrawCel 132 10 0 6 (+ (* i 11) 12) -1)
			(DrawCel 132 10 1 176 (+ (* i 11) 12) -1)
		)
	)
)

(instance startPanel of GameControls
	
	(method (init)
		(= window startWin)
		(self
			add: introItem playItem restoreItem instructItem quitItem
			eachElementDo: #signal 387
		)
		(playItem signal: (| (playItem signal?) HIDEBAR))
		(quitItem signal: (| (quitItem signal?) HIDEBAR))
		(super init: &rest)
	)
	
	(method (dispatchEvent)
		(= gameTime (GetTime))
		(super dispatchEvent: &rest)
	)
)

(class startItem of IconItem
	(properties
		view 132
		cel 0
		nsLeft 24
		nsTop -1
		nsRight 0
		nsBottom 0
		highlightColor 46
		lowlightColor 57
	)
	
	(method (highlight tOrF &tmp t l b r sColor)
		(= sColor
			(if (and argc tOrF) highlightColor else lowlightColor)
		)
		(= t (+ nsTop 1))
		(= l (+ nsLeft 1))
		(= b (- nsBottom 2))
		(= r (- nsRight 2))
		(Graph GDrawLine t l t r sColor -1 -1)
		(Graph GDrawLine t r b r sColor -1 -1)
		(Graph GDrawLine b r b l sColor -1 -1)
		(Graph GDrawLine b l t l sColor -1 -1)
		(Graph GShowBits (- nsTop 2) (- nsLeft 2) nsBottom (+ nsRight 3) VMAP)
	)
)

(instance introItem of startItem
	(properties
		loop 0
		nsTop 16
	)
	
	(method (select)
		(if (super select: &rest)
			(= buttonPressed TRUE)
			(startPanel state: (& (startPanel state?) $ffdf))
			(curRoom newRoom: 63)
		)
	)
)

(instance playItem of startItem
	(properties
		loop 1
		nsTop 34
	)
	
	(method (select)
		(if (super select: &rest)
			(startPanel state: (& (startPanel state?) $ffdf))
		)
	)
)

(instance restoreItem of startItem
	(properties
		loop 2
		nsTop 52
	)
	
	(method (select)
		(if (super select: &rest)
			(Palette PALIntensity 72 255 100)
			(theGame restore:)
			(Palette PALIntensity 72 255 60)
		)
	)
)

(instance instructItem of startItem
	(properties
		loop 3
		nsTop 70
	)
	
	(method (select)
		(if (super select: &rest)
			(= buttonPressed TRUE)
			(startPanel state: (& (startPanel state?) $ffdf))
			(curRoom newRoom: 102)
		)
	)
)

(instance quitItem of startItem
	(properties
		loop 4
		nsTop 88
	)
	
	(method (select)
		(if (super select: &rest)
			(= buttonPressed TRUE)
			(= quit TRUE)
		)
	)
)

(instance playPanel of GameControls
	
	(method (init)
		(= window
			((GloryWindow new:)
				top: 60
				left: 82
				bottom: 128
				right: 234
			)
		)
		(self
			add: createItem importItem cancelItem
			eachElementDo: #signal (| FIXED_POSN VICON RELVERIFY IMMEDIATE)
		)
		(cancelItem signal: (| (cancelItem signal?) HIDEBAR))
		(super init: &rest)
	)
	
	(method (dispatchEvent)
		(= gameTime (GetTime))
		(curRoom doit:)
		(super dispatchEvent: &rest)
	)
)

(instance createItem of startItem
	(properties
		loop 5
		nsLeft 2
		nsTop 5
	)
	
	(method (select)
		(if (super select: &rest)
			(= buttonPressed TRUE)
			(playPanel state: (& (playPanel state?) $ffdf))
			(startPanel state: (& (startPanel state?) $ffdf))
			(cSound fade: 0 2 5 1)
			(curRoom newRoom: 140)
		)
	)
)

(instance importItem of startItem
	(properties
		loop 6
		nsLeft 2
		nsTop 26
	)
	
	(method (select)
		(if (super select: &rest)
			(= buttonPressed TRUE)
			(playPanel state: (& (playPanel state?) $ffdf))
			(startPanel state: (& (startPanel state?) $ffdf))
			(curRoom newRoom: 54)
		)
	)
)

(instance cancelItem of startItem
	(properties
		loop 7
		nsLeft 2
		nsTop 47
	)
	
	(method (select)
		(if (super select: &rest)
			(playPanel state: (& (startPanel state?) $ffdf))
		)
	)
)

(instance newMH of EventHandler)

(instance newKH of EventHandler)
