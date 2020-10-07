;;; Sierra Script 1.0 - (do not remove this comment)
(script# PROCS)
(include game.sh)
(use Main)
(use Intrface)
(use PAvoid)
(use StopWalk)
(use Invent)
(use User)
(use System)

(public
	HandsOff 1
	HandsOn 2
	Bset 3
	Bclr 4
	Btst 5
	NormalEgo 6
	NotClose 7
	Dummy10_8 8
	Face 9
	PromptQuit 11
	VerbFail 12
	Say 13
	InitUserName 14
	RedrawCast 15
	InRoom 16
	EgoHas 17
	proc10_18 18
	PutInRoom 19
	proc10_20 20
	DoDisplay 21
	HighPrint 22
	LowPrint 23
	Measure 24
	StrFind 25
	ToLower 26
	DrawIconScroll 27
	proc10_28 28
	SetWalkSound 29
	VGAOrEGA 30
)

(procedure (HandsOff)
	(if (not isHandsOff)
		(= isHandsOff FALSE)
		(User canControl: FALSE canInput: FALSE)
		(ego setMotion: 0)
		(theIconBar disable:
			ICON_DO
			ICON_LOOK
			ICON_USE
			ICON_BOOK
			ICON_MAP
			ICON_CONTROL
			ICON_WHAT
		)
		(theIconBar curIcon: (theIconBar at: ICON_DO))
		(theGame setCursor: INVIS_CURSOR TRUE)
		(if iconBarActive (theIconBar show:))
	)
	(return (++ isHandsOff))
)

(procedure (HandsOn allHands)
	(if (and argc allHands)
		(= isHandsOff TRUE)
	)
	(if (<= (-- isHandsOff) 0)
		(= isHandsOff FALSE)
		(User canControl: TRUE canInput: TRUE)
		(theIconBar enable:
			ICON_DO
			ICON_LOOK
			ICON_USE
			ICON_BOOK
			ICON_MAP
			ICON_CONTROL
			ICON_WHAT
			curIcon: (theIconBar at: ICON_DO)
		)
		(theGame setCursor: ((theIconBar at: ICON_DO) cursor?) TRUE)
		(if iconBarActive
			(theIconBar show:)
		)
	)
)

(procedure (Bset flagEnum)
	(= [gameFlags (/ flagEnum 16)]
		(|
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (Bclr flagEnum)
	(= [gameFlags (/ flagEnum 16)]
		(&
			[gameFlags (/ flagEnum 16)]
			(~ (>> $8000 (mod flagEnum 16)))
		)
	)
)

(procedure (Btst flagEnum)
	(return
		(&
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (NormalEgo theLoop theView param3 &tmp temp0)
	(= temp0 0)
	(if (> argc 0)
		(ego loop: theLoop)
		(if (> argc 1)
			(ego view: theView)
			(if (> argc 2)
				(= temp0 param3)
			)
		)
	)
	(if (not temp0)
		(= temp0 1)
	)
	(ego
		setLoop: -1
		selection: 0
		setPri: -1
		setMotion: 0
		setCycle: StopWalk
		setStep: 3 2
		setAvoider: PAvoider
		ignoreActors: FALSE
		moveSpeed: (theGame egoMoveSpeed?)
		cycleSpeed: (theGame egoMoveSpeed?)
	)
)

(procedure (NotClose)
	(Print 10 0)
)

(procedure (Dummy10_8)
)

(procedure (Face actor1 actor2 both whoToCue &tmp ang1To2 theX theY obj)
	(= obj 0)
	(if (IsObject actor2)
		(= theX (actor2 x?))
		(= theY (actor2 y?))
		(if (== argc 3)
			(= obj both)
		)
	else
		(= theX actor2)
		(= theY both)
		(if (== argc 4)
			(= obj whoToCue)
		)
	)
	(= ang1To2 (GetAngle (actor1 x?) (actor1 y?) theX theY))
	(actor1 setHeading: ang1To2 (if (IsObject obj) obj else 0))
)

(procedure (PromptQuit)
	(= quit
		(Print 10 1
			#title {Good-bye}
			#button {Stop} 1
			#button {Keep Playing} 0
			#icon egoView 0 0
		)
	)
)

(procedure (VerbFail)
)

(procedure (Say param1 who whom param4 param5 modNum msgNum &tmp temp0 tObj tID evt)
	(= tObj (who talkerObj?))
	(= tID (who talkerID?))
	(cond 
		((not whom) 0)
		((>= (whom x?) (who x?))
			(tObj facingDir: dirE))
		((< (whom x?) (who x?))
			(tObj facingDir: dirW)
		)
		(else
			(tObj facingDir: dirE)
		)
	)
	(cond 
		((OneOf tID 2 4 3)
			(= selectedStory theCindStory)
		)
		((== tID 5)
			(= selectedStory theJackStory)
		)
		((OneOf tID 6 7 8 9 10 11)
			(= selectedStory theBremenStory)
		)
		((OneOf tID 12 13 14)
			(= selectedStory theBeautyStory)
		)
		((OneOf tID 15 16 17)
			(= selectedStory theSnowStory)
		)
	)
	(if (or (== whom ego) (== who ego))
		(if (== who ego)
			(Face ego whom)
		else
			(Face ego who)
		)
		(if (ego head?)
			(ego doit:)
			((ego head?) doit:)
		)
		(RedrawCast)
		(RedrawCast)
	)
	(if (!= tID global148)
		(if (IsObject global149)
			(global149 dispose:)
		)
		(= global148 tID)
		((= global149 tObj) show: param1)
	)
	(tObj
		say: modNum msgNum 0 param5 (if (== param4 -1) 0 else param4)
	)
	(if (== param4 -1)
		(while fastCast
			(fastCast eachElementDo: #doit)
			(if (and ((= evt (Event new:)) type?) fastCast)
				(fastCast firstTrue: #handleEvent evt)
			)
			(evt dispose:)
		)
	)
)

(procedure (InitUserName)
	(StrCpy @userName {_})
)

(procedure (RedrawCast)
	(Animate (cast elements?) FALSE)
)

(procedure (InRoom what &tmp i inventorySize temp2)
	(= inventorySize (inventory size?))
	(= i 0)
	(while (< i inventorySize)
		(if
			(and
				(==
					((= temp2 (inventory at: i)) owner?)
					curRoomNum
				)
				(== (temp2 superClass?) what)
			)
			(return temp2)
		)
		(++ i)
	)
	(return 0)
)

(procedure (EgoHas what &tmp i inventorySize temp2)
	(= inventorySize (inventory size?))
	(= i 0)
	(while (< i inventorySize)
		(if
			(and
				(== ((= temp2 (inventory at: i)) owner?) ego)
				(== (temp2 superClass?) what)
			)
			(return temp2)
		)
		(++ i)
	)
	(return 0)
)

(procedure (proc10_18 &tmp i theStorySetSize temp2)
	(= theStorySetSize (theStorySet size?))
	(= i 0)
	(while (< i theStorySetSize)
		(if
			(and
				((= temp2 (theStorySet at: i)) selected?)
				(not (temp2 done?))
			)
			(return 1)
		)
		(++ i)
	)
	(return 0)
)

(procedure (PutInRoom what &tmp temp0 i)
	(= temp0 (Random 1 [global129 0]))
	(what owner: [global129 temp0])
	(= i temp0)
	(while (< i [global129 0])
		(= [global129 i] [global129 (+ i 1)])
		(++ i)
	)
	(return (-- [global129 0]))
)

(procedure (proc10_20 param1 &tmp temp0 i)
	(= temp0 (Random 1 [global134 0]))
	(param1 owner: [global134 temp0])
	(= i temp0)
	(while (< i [global134 0])
		(= [global134 i] [global134 (+ i 1)])
		(++ i)
	)
	(return (-- [global134 0]))
)

(procedure (DoDisplay theString theColor theX theY)
	(Display theString
		p_at (+ theX 1) theY
		p_color (+ theColor 2)
		p_mode teJustLeft
		&rest
	)
	(Display theString
		p_at theX theY
		p_color theColor
		p_mode teJustLeft
		&rest
	)
)

(procedure (HighPrint)
	(Print &rest #at -1 12)
)

(procedure (LowPrint)
	(Print &rest #at -1 158)
)

(procedure (Measure findLen theString &tmp strAddr [str 500] [printRect 4])
	(if (u< (= strAddr theString) 1000)
		(= strAddr @str)
		(Format @str theString &rest)
	)
	(TextSize @[printRect 0] strAddr findLen 0)
	(= global227 (- [printRect 3] [printRect 1]))
	(= global228 (- [printRect 2] [printRect 0]))
	(return global227)
)

(procedure (StrFind theString theWord &tmp len wordLen mainPtr wordPtr)
	(= wordLen (StrLen theWord))
	(= len (- (= len (StrLen theString)) wordLen))
	(while (>= len 0)
		(= wordPtr 0)
		(= mainPtr len)
		(while (< wordPtr wordLen)
			(if
				(!=
					(ToLower (StrAt theWord wordPtr))
					(ToLower (StrAt theString mainPtr))
				)
				(break)
			)
			(++ wordPtr)
			(++ mainPtr)
		)
		(if (== wordPtr wordLen)
			(return (+ wordPtr 1))
		)
		(-- len)
	)
	(return 0)
)

(procedure (ToLower aChar)
	(return
		(if (or (< aChar `A) (> aChar `Z))
			(return aChar)
		else
			(return (+ (- aChar `A) `a))
		)
	)
)

(procedure (DrawIconScroll)
	(if iconBarActive
		(SetPort -1)
		(DrawCel 900 9 0 0 0 15)
		(DrawCel 900 9 1 277 0 15)
		(SetPort 0)
	)
)

(procedure (proc10_28 &tmp temp0 i)
	(= i 0)
	(while (< i (Inventory size?))
		(if (== ((= temp0 (Inventory at: i)) owner?) ego)
			(break)
		)
		(= temp0 0)
		(++ i)
	)
	(theIconBar curInvIcon: temp0 show:)
)

(procedure (SetWalkSound)
	(cond 
		((and (not (theMusic2 handle?)) (> walkSound 0))
			(if (== walkSound 6)
				(theGame setScript: theWalkMusic)
			else
				(theMusic2
					number: walkSound
					priority: 15
					setLoop: -1
					play:
				)
				(= walkSound 0)
			)
		)
		(
			(and
				(theMusic2 handle?)
				(== (theMusic2 number?) 37)
				(== walkSound 6)
			)
			(theMusic2 stop:)
			(theGame setScript: theWalkMusic)
		)
	)
)

(procedure (VGAOrEGA vga ega)
	(return (if (>= numColors 256) vga else ega))
)
