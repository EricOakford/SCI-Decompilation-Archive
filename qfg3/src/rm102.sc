;;; Sierra Script 1.0 - (do not remove this comment)
(script# 102)
(include game.sh) (include "102.shm")
(use Main)
(use GloryWindow)
(use TellerIcon)
(use Talker)
(use Feature)
(use GControl)
(use Game)
(use User)
(use System)

(public
	rm102 0
)

(local
	infoTellMainBranch = [0 -2 -3 999]
	infoTell1 = [0 4 5 6 7 999]
	infoTell2 = [0 -8 -9 -10 999]
	local15 = [0 11 12 13 14 999]
	local21 = [0 15 16 17 18 999]
	local27 = [0 19 20 -21 999]
	local32 = [0 25 26 28 23 24 999]
	infoTellKeys = [0 -2 -3 -8 -9 -10 -21 999]
	[infoTellTree 8]
)
(instance rm102 of Room
	(properties
		picture 130
	)
	
	(method (init)
		(User canInput: TRUE)
		(theGame setCursor: ARROW_CURSOR)
		(super init: &rest)
		(= [infoTellTree 0] @infoTellMainBranch)
		(= [infoTellTree 1] @infoTell1)
		(= [infoTellTree 2] @infoTell2)
		(= [infoTellTree 3] @local15)
		(= [infoTellTree 4] @local21)
		(= [infoTellTree 5] @local27)
		(= [infoTellTree 6] @local32)
		(Narrator talkWidth: 250)
		(infoTeller init: info @infoTellMainBranch @infoTellTree @infoTellKeys)
		(info init:)
		(Animate (cast elements?) 0)
		(infoTeller doVerb: 2)
	)
)

(instance infoTeller of Teller
	
	(method (respond)
		(= query (self showDialog:))
		(if (and (!= query -999) iconValue)
			(= query iconValue)
		)
		(cond 
			((== query -999)
				(return (curRoom newRoom: 130))
			)
			((== query 999)
				(self doParent:)
				(return FALSE)
			)			
			((and (< query 0) (not (self doChild: query)))
				(return TRUE)
			)
		)
		(= query (Abs query))
		(messager say: (client noun?) V_TELL query 0 self)
		(return 1)
	)
	
	(method (doDialog param1 &tmp temp0 temp1 temp2 temp3 newInfoIcon temp5 temp6 temp7 temp8 newList temp10 temp11 temp12 temp13 temp14 [str 30])
		(= temp0 [param1 (= temp3 0)])
		(= temp5 5)
		(= temp8 0)
		(= newInfoIcon 0)
		(= newList (List new:))
		((= theControls (GameControls new:))
			window:
				((GloryWindow new:)
					top: 40
					left: 85
					bottom: 140
					right: 235
					priority: 15
					yourself:
				)
		)
		(if (OneOf curArray @infoTellMainBranch @infoTell2)
			(+= temp5 15)
		)
		(= temp2 1)
		(while (!= (WordAt curArray temp2) 999)
			(= temp6 1)
			(= temp3 1)
			(while (and temp6 (< temp3 argc))
				(if
					(and
						(== (WordAt curArray temp2) [param1 temp3])
						(not [param1 (+ temp3 1)])
					)
					(= temp6 0)
				)
				(+= temp3 2)
			)
			(if temp6
				(= temp7 (WordAt curArray temp2))
				(= temp14 (self getSeqNum: (Abs temp7)))
				(if
					(not
						(= temp13
							(Message MsgSize
								curRoomNum
								(client noun?)
								2
								(Abs temp7)
								temp14
							)
						)
					)
					(break)
				)
				(= temp1 (Memory MNewPtr temp13))
				(newList add: temp1)
				(Message MsgGet
					curRoomNum
					(client noun?)
					2
					(Abs temp7)
					temp14
					temp1
				)
				((= newInfoIcon (infoIcon new:))
					myHandle: temp1
					value: temp7
					nsTop: temp5
					myTeller: self
				)
				(if (!= client ego) (newInfoIcon notEgo: 1))
				(theControls add: newInfoIcon)
				(++ temp8)
				(+= temp5 15)
			)
			(++ temp2)
		)
		(if (not (IsObject newInfoIcon))
			((theControls window?) dispose:)
			(theControls dispose:)
			(newList dispose:)
			(return -999)
		)
		(= newInfoIcon (infoIcon new:))
		(if (not temp0)
			(Message MsgGet 23 0 0 2 1 @str)
			(newInfoIcon
				myHandle: @str
				value: 999
				nsTop: temp5
				myTeller: self
			)
		else
			(Message MsgGet 23 0 0 3 1 @str)
			(newInfoIcon
				myHandle: @str
				value: -999
				nsTop: temp5
				myTeller: self
			)
		)
		(theControls add: newInfoIcon)
		((theControls window?)
			top: (- 60 (* 7 temp8))
			bottom: (- (+ temp5 80) (* 7 temp8))
		)
		(theControls init: show: dispose:)
		(= temp10 (FirstNode (newList elements?)))
		(while temp10
			(= temp11 (NextNode temp10))
			(if (not (= temp12 (NodeValue temp10))) (break))
			(Memory MDisposePtr temp12)
			(= temp10 temp11)
		)
		(return (newList dispose:))
	)
	
	(method (doChild param1)
		(return
			(switch param1
				(-2
					(super doChild: param1)
					(self cue:)
					(return 0)
				)
				(-3
					(super doChild: param1)
					(self cue:)
					(return 0)
				)
				(-8
					(super doChild: param1)
					(self cue:)
					(return 0)
				)
				(-9
					(super doChild: param1)
					(self cue:)
					(return 0)
				)
				(-10
					(super doChild: param1)
					(self cue:)
					(return 0)
				)
				(-21
					(super doChild: param1)
					(self cue:)
					(return 0)
				)
				(else  (return 1))
			)
		)
	)
	
	(method (cue)
		(self doVerb: 2)
	)
)

(instance info of Feature
	(properties
		x 160
		y 100
		noun N_INFO
		nsBottom 199
		nsRight 319
	)
)

(instance infoIcon of TellerIcon

	(method (show &tmp [str 15])
		(if notEgo
			(cond 
				((== (infoTeller curArray?) @infoTellMainBranch)
					(Message MsgGet 102 1 2 1 1 @str)
					(Display @str
						p_at 15 5
						p_color 17
					)
				)
				((== (infoTeller curArray?) @infoTell2)
					(Message MsgGet 102 1 2 22 1 @str)
					(Display @str
						p_at 15 5
						p_color 17
					)
				)
			)
		)
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display myHandle
			p_at 20 (+ nsTop 3)
			p_color 17
		)
		(if (& signal DISABLED)
			(self mask:)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
)
