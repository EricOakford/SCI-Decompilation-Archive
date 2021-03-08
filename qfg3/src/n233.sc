;;; Sierra Script 1.0 - (do not remove this comment)
(script# 233)
(include game.sh) (include "230.shm")
(use Main)
(use GloryWindow)
(use IconBar)
(use GControl)

(public
	ExchangeMoney 0
)

(local
	buttonPressed
	yesText
	noText
)
(procedure (ExchangeMoney &tmp len oldCur)
	(= oldCur theCursor)
	(= len (Message MsgSize 230 N_MONEY_CHANGER V_DOIT C_EXCHANGE_MONEY 1))
	(= yesText (Memory MNewPtr len))
	(Message MsgGet 230 N_MONEY_CHANGER V_DOIT C_EXCHANGE_MONEY 1 yesText)
	(= len (Message MsgSize 230 N_MONEY_CHANGER V_DOIT 71 1))
	(= noText (Memory MNewPtr len))
	(Message MsgGet 230 N_MONEY_CHANGER V_DOIT C_EXCHANGE_DONE 1 noText)
	(quest init: show: dispose:)
	(Memory MDisposePtr yesText)
	(Memory MDisposePtr noText)
	(if buttonPressed
		(if numDinars
			(ego solvePuzzle: fExchangedDinars 4)
			((inventory at: iRoyals)
				amount: (/ (* 9 numDinars) 10)
				message: V_ROYALS
			)
			(= numDinars 0)
		else
			((inventory at: iRoyals)
				amount: (+ ((inventory at: 0) amount?) (/ commons 100))
			)
			(= commons (mod commons 100))
		)
		(messager say: N_MONEY_CHANGER V_DOIT C_DONE_DEAL)
	)
	(theGame setCursor: oldCur)
	(DisposeScript 233)
)

(instance quest of GameControls
	
	(method (init)
		(theGame setCursor: ARROW_CURSOR)
		((= window (GloryWindow new:))
			top: 60
			left: 80
			bottom: 145
			right: 240
			priority: 15
			yourself:
		)
		(self add: moneyIcon yesIcon noIcon)
		(super init: &rest)
	)
)

(instance moneyIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 0
		signal DISABLED
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [str 30] [temp30 30] [exchangeBuf 40] [temp100 30] [temp130 30])
		(Graph GDrawLine 13 1 13 160 58 -1 -1)
		(Graph GShowBits 12 1 14 160 1)
		(Message MsgGet 230 N_MONEY_CHANGER V_DOIT C_MONEY_TITLE 1 @exchangeBuf)
		(Display @exchangeBuf p_at 5 3 p_color 0)
		(Message MsgGet 230 N_MONEY_CHANGER V_DOIT C_EXCHANGE_MONEY 3 @exchangeBuf)
		(Display @exchangeBuf p_at 0 20 p_color 17)
		(if numDinars
			(Message MsgGet 230 N_MONEY_CHANGER V_DOIT 86 1 @str)
			(Format @temp100 @str numDinars)
			(Display @temp100 p_at 0 36 p_color 19)
		else
			(Message MsgGet 230 N_MONEY_CHANGER V_DOIT 87 1 @str)
			(Format @temp100 @str ((inventory at: iRoyals) amount?))
			(Message MsgGet 230 N_MONEY_CHANGER V_DOIT 88 1 @temp30)
			(Format @temp130 @temp30 commons)
			(Display @temp100 p_at 0 30 p_color 19)
			(Display @temp130 p_at 0 40 p_color 19)
		)
	)
)

(instance yesIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 55
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show)
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display yesText p_at 20 (+ nsTop 3) p_color 17)
		(if (& signal DISABLED)
			(self mask:)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= buttonPressed TRUE)
		(quest state: (& (quest state?) $ffdf))
	)
	
	(method (highlight tOrF &tmp sColor)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= sColor 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= sColor 17)
		)
		(Display yesText p_at 20 (+ nsTop 3) p_color sColor)
	)
)

(instance noIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 70
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [str 15])
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display noText p_at 20 (+ nsTop 3) p_color 17)
		(if (& signal DISABLED)
			(self mask:)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= buttonPressed 0)
		(quest state: (& (quest state?) $ffdf))
	)
	
	(method (highlight tOrF &tmp sColor)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= sColor 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= sColor 17)
		)
		(Display noText p_at 20 (+ nsTop 3) p_color sColor)
	)
)
