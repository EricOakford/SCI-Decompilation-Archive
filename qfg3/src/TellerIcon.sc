;;; Sierra Script 1.0 - (do not remove this comment)
(script# TELLER) ;23
(include game.sh) (include "23.shm")
(use Main)
(use GloryWindow)
(use IconBar)
(use GControl)
(use System)

(public
	Teller 0
)

(procedure (localproc_077b param1 param2 param3)
	(Memory MReadWord (+ param1 (* 2 param2)) param3)
)

(class TellerIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 15
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
		maskCel 0
		myHandle 0
		value 0
		notEgo 0
		myTeller 0
	)
	
	(method (show &tmp [str 15])
		(if notEgo
			(Message MsgGet TELLER NULL NULL C_ASK_ABOUT 1 @str)
			(Display @str p_at 15 5 p_color 17)
		)
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display myHandle p_at 20 (+ nsTop 3) p_color 17)
		(if (& signal DISABLED)
			(self mask:)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(myTeller iconValue: value)
		((myTeller theControls?)
			state: (& ((myTeller theControls?) state?) $ffdf)
		)
		(if
			(and
				(or
					(== self ((myTeller theControls?) at: 0))
					(== self ((myTeller theControls?) at: 1))
				)
				(not notEgo)
			)
			(ego addHonor: 1 useSkill: COMM 10)
		else
			(ego useSkill: COMM 5)
		)
	)
	
	(method (highlight tOrF &tmp sColor)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= sColor 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= sColor 17)
		)
		(Display myHandle p_at 20 (+ nsTop 3) p_color sColor)
	)
)

(class Teller of Object
	(properties
		query 0
		curArray 0
		arrays 0
		keys 0
		client 0
		iconValue 0
		theControls 0
	)
	
	(method (init theClient theCurArray theArrays theKeys)
		(= client theClient)
		(= curArray theCurArray)
		(= arrays theArrays)
		(if (> argc 3)
			(= keys theKeys)
		)
		(client actions: self)
		(super init:)
	)
	
	(method (respond)
		(= query 0)
		(self showDialog:)
		(if (and (!= query -999) iconValue)
			(= query iconValue)
		)
		(cond 
			((or (not query) (== query -999))
				(return TRUE)
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
		(if query
			(messager say: (client noun?) V_TELL query 0)
		)
		(return TRUE)
	)
	
	(method (showDialog)
		(self
			doDialog: (if (== (WordAt arrays 0) curArray) 1 else 0) &rest
		)
	)
	
	(method (doDialog param1 &tmp
					temp0 temp1 temp2 temp3 newTellerIcon
					top temp6 val temp8 newList temp10 temp11
					temp12 temp13 temp14 [str 30] temp45)
		(= temp45 150)
		(= temp0 [param1 (= temp3 0)])
		(= top 5)
		(= temp8 0)
		(= newTellerIcon 0)
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
		(if (!= client ego)
			(+= top 15)
		)
		(for ((= temp2 1)) (!= (WordAt curArray temp2) 999) ((++ temp2))
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
				(= val (WordAt curArray temp2))
				(= temp14 (self getSeqNum: (Abs val)))
				(if
					(not
						(= temp13
							(Message MsgSize curRoomNum (client noun?) 2 (Abs val) temp14)
						)
					)
					(break)
				)
				(= temp1 (Memory MNewPtr temp13))
				(if (> (= temp13 (+ (* temp13 7) 20)) temp45)
					(= temp45 temp13)
				)
				(newList add: temp1)
				(Message MsgGet curRoomNum (client noun?) V_TALK (Abs val) temp14 temp1)
				((= newTellerIcon (TellerIcon new:))
					myHandle: temp1
					value: val
					nsTop: top
					myTeller: self
				)
				(if (!= client ego) 
					(newTellerIcon notEgo: TRUE)
				)
				(theControls add: newTellerIcon)
				(++ temp8)
				(+= top 15)
			)
		)
		(if (not (IsObject newTellerIcon))
			(if (== client ego)
				(client doVerb: V_TALK)
			else
				(messager say: N_PERSON V_DOIT NULL 0 0 TELLER)
			)
			((theControls window?) dispose:)
			(theControls dispose:)
			(newList dispose:)
			(return -999)
		)
		(= newTellerIcon (TellerIcon new:))
		(if (not temp0)
			(Message MsgGet TELLER NULL NULL C_SOMETHING_ELSE 1 @str)
			(newTellerIcon
				myHandle: @str
				value: 999
				nsTop: top
				myTeller: self
			)
		else
			(Message MsgGet TELLER NULL NULL C_ENOUGH_ALREADY 1 @str)
			(newTellerIcon
				myHandle: @str
				value: -999
				nsTop: top
				myTeller: self
			)
		)
		(theControls add: newTellerIcon)
		((theControls window?)
			top: (- 60 (* 7 temp8))
			bottom: (- (+ top 80) (* 7 temp8))
			left: (- 160 (/ temp45 2))
			right: (+ 160 (/ temp45 2))
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
	
	(method (getSeqNum)
		(return TRUE)
	)
	
	(method (doChild param1 &tmp temp0)
		(= temp0 0)
		(while (++ temp0)
			(if (== (WordAt keys temp0) param1)
				(self stuffArray: (WordAt arrays temp0) 1)
				(return 1)
			)
			(< (WordAt keys temp0) 999)
		)
		(return 1)
	)
	
	(method (doParent)
		(self stuffArray: (WordAt curArray 0) 0)
	)
	
	(method (stuffArray theCurArray param2)
		(if param2 (localproc_077b theCurArray 0 curArray))
		(= curArray theCurArray)
		(return param2)
	)
	
	(method (doVerb theVerb &tmp clientModNum)
		(return
			(cond 
				((and (IsObject client) (== theVerb V_TALK))
					(= iconValue 0)
					(repeat (if (self respond:) (break)))
				)
				((== client ego)
					(client doVerb: theVerb)
				)
				(else
					(if (IsObject client)
						(if (> (client modNum?) -1)
							(= clientModNum (client modNum?))
						else
							(= clientModNum curRoomNum)
						)
						(if (Message MsgSize clientModNum (client noun?) theVerb NULL 1)
							(messager say: (client noun?) theVerb 0 0 clientModNum)
							(return TRUE)
						)
					)
					(cond 
						((== theVerb V_TALK)
							(messager say: N_PERSON V_TALK 0 0 0 TELLER)
						)
						((== theVerb V_LOOK)
							(messager say: N_PERSON V_LOOK NULL 0 0 TELLER)
						)
						((== theVerb V_DO)
							(messager say: N_PERSON V_DO NULL 0 0 TELLER)
						)
						(
							(OneOf theVerb
								V_CALM V_DAZZLE V_DETECT V_FETCH V_FLAME
								V_FORCEBOLT V_HEAL V_JUGGLE V_LEVITATE V_LIGHTNING
								V_OPEN V_REVERSAL V_STAFF V_TRIGGER V_ZAP
							)
							(messager say: N_CUE V_DOIT C_CAST_SPELL 0 0 TELLER)
						)
						((OneOf theVerb V_BEADS V_DINARS V_GEM V_HEARTGIFT V_NOTE V_OPAL V_PIN V_ROBE V_ROYALS V_ZEBRA_SKINS)
							(messager say: N_CUE V_DOIT C_INAPPROPRIATE_OFFERING 0 0 TELLER)
						)
						((OneOf theVerb V_SWORD V_DAGGER V_FINE_DAGGER V_FINE_SPEAR V_MAGIC_SPEAR V_ROCK)
							(messager say: N_CUE V_DOIT C_HURT_PERSON 0 0 TELLER)
						)
						(else
							(messager say: N_CUE V_DOIT C_PERSON_UNINTERESTED 0 0 TELLER)
						)
					)
				)
			)
		)
	)
	
	(method (cue)
	)
)
