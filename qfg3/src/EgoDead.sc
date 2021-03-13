;;; Sierra Script 1.0 - (do not remove this comment)
(script# EGODEAD) ;26
(include game.sh) (include "26.shm")
(use Main)
(use GloryWindow)
(use Intrface)
(use Print)
(use Dialog)
(use IconBar)
(use DCIcon)
(use GControl)
(use Motion)
(use System)

(public
	EgoDead 0
)

(local
	local0 =  400
	deathReason
	deathRoom
	deathMusic
	printRet
	[restoreBuf 10]
	[restartBuf 10]
	[quitBuf 5]
	[backBuf 20]
	local50
	local51
	local52
	deathView
	local54
	local55
	local56
	[local57 30]
)
(procedure (EgoDead reason roomNum deadView theCycler deadSnd)
	(HandsOff)
	(if (not (Btst fFakeDeath))
		(if (Btst fEgoHasDied)
			(return FALSE)
		else
			(Bset fEgoHasDied)
		)
	)
	(theGame setCursor: normalCursor TRUE)
	(= local54 0)
	(= local55 152)
	(if (>= argc 1)
		(= deathReason reason)
		(if (>= argc 2)
			(if roomNum
				(= deathRoom roomNum)
			else
				(= deathRoom curRoomNum)
			)
			(if (>= argc 3)
				(= deathView deadView)
				(if (>= argc 4)
					(= local54 theCycler)
					(if (== argc 5) (= local55 deadSnd))
				)
			else
				(= deathView 937)
			)
		else
			(= deathRoom curRoomNum)
			(= deathView 937)
		)
	else
		(= deathView 937)
		(= deathRoom 26)
		(= deathReason 1)
	)
	(deathViewIcon view: deathView)
	(= local50
		(-
			200
			(= local56
				(+
					(CelWide (deathViewIcon view?) (deathViewIcon loop?))
					10
				)
			)
		)
	)
	(= local51
		(+
			(*
				(+
					(/
						(Message MsgSize deathRoom 0 63 deathReason 1)
						(/ local50 6)
					)
					4
				)
				9
			)
			5
		)
	)
	(if
		(>
			(= local52
				(+
					(CelHigh (deathViewIcon view?) (deathViewIcon loop?))
					25
				)
			)
			local51
		)
		(= local51 local52)
	)
	(= deathMusic (cSound number?))
	(sounds eachElementDo: #fade 0 1 5 1)
	(DIcon state: (| (DIcon state?) dIcon))
	(SetCursor -2)
	(return (DeathMenu))
)


(procedure (DeathMenu)
	(asm
		pushi    7
		pushi    0
		pushi    26
		pushi    1
		pushi    0
		pushi    3
		pushi    1
		lea      @restoreBuf
		push    
		callk    Message,  14
		pushi    7
		pushi    0
		pushi    26
		pushi    1
		pushi    0
		pushi    4
		pushi    1
		lea      @restartBuf
		push    
		callk    Message,  14
		pushi    7
		pushi    0
		pushi    26
		pushi    1
		pushi    0
		pushi    5
		pushi    1
		lea      @quitBuf
		push    
		callk    Message,  14
		pushi    7
		pushi    0
		pushi    26
		pushi    1
		pushi    0
		pushi    6
		pushi    1
		lea      @backBuf
		push    
		callk    Message,  14
code_01ec:
		lsl      printRet
		dup     
		ldi      1
		eq?     
		bnt      code_0256
		pushi    1
		pushi    140
		callb    Btst,  2
		bnt      code_0240
		pushi    1
		pushi    140
		callb    Bclr,  2
		pushi    #say
		pushi    6
		pushi    0
		pushi    63
		lsl      deathReason
		pushi    3
		pushi    0
		lsl      deathRoom
		lag      messager
		send     16
		pushi    1
		lofsa    deathViewIcon
		push    
		callk    IsObject,  2
		bnt      code_0229
		pushi    #dispose
		pushi    0
		lofsa    deathViewIcon
		send     4
code_0229:
		pushi    #number
		pushi    1
		lsl      deathMusic
		pushi    155
		pushi    1
		pushi    1
		pushi    39
		pushi    0
		lag      cSound
		send     16
		jmp      code_03cb
		jmp      code_03b0
code_0240:
		pushi    4
		dup     
		pushi    0
		pushi    255
		pushi    100
		callk    Palette,  8
		pushi    #restart
		pushi    0
		lag      theGame
		send     4
		jmp      code_03b0
code_0256:
		dup     
		ldi      2
		eq?     
		bnt      code_02be
		pushi    1
		pushi    140
		callb    Btst,  2
		bnt      code_02a8
		pushi    1
		pushi    140
		callb    Bclr,  2
		pushi    #say
		pushi    6
		pushi    0
		pushi    63
		lsl      deathReason
		pushi    3
		pushi    0
		lsl      deathRoom
		lag      messager
		send     16
		pushi    1
		lofsa    deathViewIcon
		push    
		callk    IsObject,  2
		bnt      code_0291
		pushi    #dispose
		pushi    0
		lofsa    deathViewIcon
		send     4
code_0291:
		pushi    #number
		pushi    1
		lsl      deathMusic
		pushi    155
		pushi    1
		pushi    1
		pushi    39
		pushi    0
		lag      cSound
		send     16
		jmp      code_03cb
		jmp      code_03b0
code_02a8:
		pushi    4
		dup     
		pushi    0
		pushi    255
		pushi    100
		callk    Palette,  8
		pushi    #restore
		pushi    0
		lag      theGame
		send     4
		jmp      code_03b0
code_02be:
		dup     
		ldi      3
		eq?     
		bnt      code_030d
		pushi    1
		pushi    140
		callb    Btst,  2
		bnt      code_0303
		pushi    1
		pushi    140
		callb    Bclr,  2
		pushi    #say
		pushi    6
		pushi    0
		pushi    63
		lsl      deathReason
		pushi    3
		pushi    0
		lsl      deathRoom
		lag      messager
		send     16
		pushi    #dispose
		pushi    0
		lofsa    deathViewIcon
		send     4
		pushi    #number
		pushi    1
		lsl      deathMusic
		pushi    155
		pushi    1
		pushi    1
		pushi    39
		pushi    0
		lag      cSound
		send     16
		jmp      code_03cb
code_0303:
		ldi      1
		sag      quit
		jmp      code_03cb
		jmp      code_03b0
code_030d:
		lsl      deathView
		ldi      937
		eq?     
		bnt      code_032c
		pushi    #cel
		pushi    1
		pushi    0
		pushi    248
		pushi    1
		pushi    #new
		pushi    0
		class    EndLoop
		send     4
		push    
		lofsa    deathViewIcon
		send     12
		jmp      code_033f
code_032c:
		lal      local54
		bnt      code_033f
		pushi    #cycler
		pushi    1
		pushi    #new
		pushi    0
		send     4
		push    
		lofsa    deathViewIcon
		send     6
code_033f:
		pushi    #cycler
		pushi    0
		lofsa    deathViewIcon
		send     4
		bnt      code_035c
		pushi    #init
		pushi    1
		lofsa    deathViewIcon
		push    
		pushi    #cycler
		pushi    0
		lofsa    deathViewIcon
		send     4
		send     6
code_035c:
		pushi    #number
		pushi    1
		lsl      local55
		pushi    155
		pushi    1
		pushi    1
		pushi    172
		pushi    1
		pushi    80
		pushi    39
		pushi    0
		lag      cSound
		send     22
		pushi    #addIcon
		pushi    5
		pushi    935
		pushi    1
		pushi    0
		pushi    5
		pushi    0
		pushi    206
		pushi    5
		lofsa    deathViewIcon
		push    
		pushi    0
		pushi    0
		pushi    2
		pushi    22
		pushi    67
		pushi    1
		lsl      local50
		pushi    30
		pushi    1
		pushi    0
		pushi    198
		pushi    7
		pushi    0
		pushi    63
		lsl      deathReason
		pushi    1
		lsl      local56
		pushi    22
		lsl      deathRoom
		pushi    110
		pushi    0
		lofsa    myPrint
		send     62
code_03b0:
		;toss    
		pushi    #stop
		pushi    0
		lag      cSound
		send     4
		pushi    #init
		pushi    0
		pushi    217
		pushi    0
		pushi    111
		pushi    0
		lofsa    quest
		send     12
		jmp      code_01ec
code_03cb:
		ret     
	)
)

(instance deathViewIcon of DCIcon
	
	(method (init)
	)
	
	(method (draw &tmp [str 30])
		(Message MsgGet deathRoom 0 63 deathReason 2 @str)
		(Display @str
			p_at 6 6
			p_mode teJustCenter
			p_color 42
			p_font 123
			p_width 200
		)
		(Display @str
			p_at 5 6
			p_mode teJustCenter
			p_color 41
			p_font 123
			p_width 200
		)
		(super draw:)
	)
)

(instance myPrint of Print
	
	(method (showSelf &tmp default curPort i theX theY)
		(if saveCursor
			(theGame setCursor: ARROW_CURSOR)
		)
		(if (not dialog)
			(= dialog myDialog)
			(myDialog init:)
		)
		(dialog
			window: (if window else systemWindow)
			name: {PODialog}
			caller: self
		)
		(dialog text: title time: ticks setSize:)
		(dialog center:)
		(= theX
			(if (== x -1)
				(dialog nsLeft?)
			else
				x
			)
		)
		(= theY (if (== y -1) (dialog nsTop?) else y))
		(dialog moveTo: theX theY)
		(= curPort (GetPort))
		(dialog open: (if title wTitled else 0) 15)
		(return
			(if modeless
				(= modelessPort (GetPort))
				(SetPort curPort)
				(= modelessDialog dialog)
			else
				(sounds pause: TRUE)
				(cond 
					((not (= default first))
						(if
							(and
								(= default (dialog firstTrue: #checkState dActive))
								(not (dialog firstTrue: #checkState dExit))
							)
							(default state: (| (default state?) $0002))
						)
					)
					((not (IsObject default)) (= default (dialog at: default)))
				)
				(= retValue (dialog doit: default))
				(SetPort curPort)
				(cond 
					((== retValue -1)
						(= retValue 0)
					)
					((and (IsObject retValue) (retValue isKindOf: DButton))
						(= retValue (retValue value?))
					)
					((not (dialog theItem?))
						(= retValue 1)
					)
				)
				(if saveCursor
					(theGame setCursor: ((theIconBar curIcon?) cursor?))
				)
				(dialog dispose:)
				(return retValue)
			)
		)
	)
	
	(method (addIcon theViewOrObj theLoop theCel theX theY &tmp iX iY)
		(if (not dialog)
			(= dialog (myDialog new:))
		)
		(if (> argc 3)
			(= iX theX)
			(= iY theY)
		else
			(= iX (= iY 0))
		)
		(if (IsObject theViewOrObj)
			(dialog
				add: (theViewOrObj
					setSize:
					moveTo: (+ iX MARGIN) (+ iY MARGIN)
					yourself:
				)
				setSize:
			)
		else
			(dialog
				add:
					((DIcon new:)
						view: theViewOrObj
						loop: theLoop
						cel: theCel
						setSize:
						moveTo: (+ iX MARGIN) (+ iY MARGIN)
						yourself:
					)
				setSize:
			)
		)
	)
)

(instance myDialog of Dialog
	
	(method (doit def &tmp event ret done)
		(= gameTime (+ tickOffset (GetTime)))
		(= done 0)
		(self eachElementDo: #init)
		(if theItem
			(theItem select: 0)
		)
		(= theItem
			(if (and argc def)
				def
			else
				(self firstTrue: #checkState 1)
			)
		)
		(if theItem
			(theItem select: 1)
		)
		(if (not theItem)
			(= eatTheMice eatMice)
			(= lastTicks (GetTime))
		else
			(= eatTheMice 0)
		)
		(= ret 0)
		(while (not ret)
			(= gameTime (+ tickOffset (GetTime)))
			(if (< (-- local0) 0)
				(= local0 400)
				(return ret)
			)
			(self eachElementDo: #cycle)
			(= event ((Event new:) localize:))
			(if eatTheMice
				(-- eatTheMice)
				(if (== (event type?) mouseDown)
					(event type: nullEvt)
				)
				(while (== lastTicks (GetTime))
				)
				(= lastTicks (GetTime))
			)
			(= ret (self handleEvent: event))
			(event dispose:)
			(if (self check:) (break))
			(if (== ret -2)
				(= ret 0)
				(EditControl theItem 0)
				(break)
			)
			(Wait 1)
		)
		(return ret)
	)
)

(instance quest of GameControls
	
	(method (init)
		(theGame setCursor: ARROW_CURSOR)
		((= window (GloryWindow new:))
			top: 50
			left: 85
			bottom: 125
			right: 235
			priority: 15
			yourself:
		)
		(self
			add: titleIcon backIcon restartIcon restoreIcon quitIcon
		)
		(super init: &rest)
	)
)

(instance titleIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 0
		signal DISABLED
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [str 20])
		(Message MsgGet EGODEAD N_DEATH NULL C_SELECT_WHICH 1 @str)
		(Display @str p_at 5 3 p_color 17)
	)
)

(instance backIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 15
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [str 15])
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @backBuf p_at 20 (+ nsTop 3) p_color 17)
		(if (& signal DISABLED)
			(self mask:)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= printRet 0)
		(quest state: (& (quest state?) $ffdf))
	)
	
	(method (highlight tOrF &tmp theColor)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= theColor 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= theColor 17)
		)
		(Display @backBuf p_at 20 (+ nsTop 3) p_color theColor)
	)
)

(instance restoreIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 45
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [str 15])
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @restoreBuf p_at 20 (+ nsTop 3) p_color 17)
		(if (& signal DISABLED)
			(self mask:)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= printRet 2)
		(quest state: (& (quest state?) $ffdf))
	)
	
	(method (highlight tOrF &tmp theColor)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= theColor 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= theColor 17)
		)
		(Display @restoreBuf p_at 20 (+ nsTop 3) p_color theColor)
	)
)

(instance restartIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 30
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show)
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @restartBuf p_at 20 (+ nsTop 3) p_color 17)
		(if (& signal DISABLED)
			(self mask:)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= printRet 1)
		(quest state: (& (quest state?) $ffdf))
	)
	
	(method (highlight tOrF &tmp theColor)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= theColor 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= theColor 17)
		)
		(Display @restartBuf p_at 20 (+ nsTop 3) p_color theColor)
	)
)

(instance quitIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 60
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [str 15])
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @quitBuf p_at 20 (+ nsTop 3) p_color 17)
		(if (& signal DISABLED)
			(self mask:)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= printRet 3)
		(quest state: (& (quest state?) $ffdf))
	)
	
	(method (highlight tOrF &tmp theColor)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= theColor 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= theColor 17)
		)
		(Display @quitBuf p_at 20 (+ nsTop 3) p_color theColor)
	)
)
