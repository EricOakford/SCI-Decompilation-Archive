;;; Sierra Script 1.0 - (do not remove this comment)
(script# VENDOR) ;51
(include game.sh) (include "47.shm")
(use Main)
(use GloryWindow)
(use IconBar)
(use GControl)
(use System)

(public
	Vendor 0
	Ware 1
	barter 2
	Buy 3
)

(local
	local0
	local1 = [3 128 3 128]
	local5 = [18 18 36 36]
	local9
	bargainPrice
	wareQuantity
	warePrice
	theIconI
	local14
	theVendor
	local16
	saveBits
	local18
	local19
	local20
	local21
	local22
	local23
	local24
	[itemBuf 30]
	[priceBuf 30]
	saveCursor
	local86
)
(procedure (Buy what howMany &tmp temp0 haveR [str 20])
	(= local0 1)
	(soundFx number: 260 setLoop: 4 play:)
	(= haveR (inventory at: iRoyals))
	(cond 
		(
			(==
				((= temp0 ((theVendor goods?) at: what)) denomination?)
				100
			)
			(if (<= (* (temp0 price?) howMany) (haveR amount?))
				(haveR
					amount: (- (haveR amount?) (* (temp0 price?) howMany))
				)
			else
				(= commons (+ commons (* (haveR amount?) 100)))
				(haveR amount: 0)
				(= commons (- commons (* (temp0 price?) howMany 100)))
			)
		)
		((<= (* (temp0 price?) howMany) commons)
			(= commons (- commons (* (temp0 price?) howMany)))
		)
		(else
			(while (> (* (temp0 price?) howMany) commons)
				(= commons (+ commons 100))
				(haveR amount: (- (haveR amount?) 1))
			)
			(= commons (- commons (* (temp0 price?) howMany)))
		)
	)
	(((theVendor goods?) at: what)
		quantity: (- (((theVendor goods?) at: what) quantity?) howMany)
	)
	(if (and (haveR amount?) (== (haveR message?) 59))
		(haveR message: 10)
	)
	(if
		(and
			(not commons)
			(not (haveR amount?))
			numDinars
			(== (haveR message?) 10)
		)
		(haveR message: 59)
	)
)

(procedure (ShowPrice)
	(Message MsgGet VENDOR N_PRICE NULL NULL 1 @priceBuf)
	(Display @priceBuf
		p_at 3 22
		p_color 1
		p_font 123
	)
	(Message MsgGet VENDOR N_PRICE NULL NULL 2 @priceBuf)
	(Display
		(Format
			@itemBuf
			@priceBuf
			((theIconI cursor?) name?)
			warePrice
			((theIconI cursor?) symbol?)
		)
		p_at 3 37
		p_color 1
		p_font 123
	)
)

(procedure (localproc_135c &tmp str)
	(= str 0)
	(if (& local9 $0001)
		(Display @str
			p_restore saveBits
		)
	else
		(if saveBits
			(Display @str
				p_restore saveBits
			)
		)
		(if (== wareQuantity 1)
			(Message MsgGet VENDOR 9 0 0 1 @priceBuf)
			(= saveBits
				(Display
					(Format
						@itemBuf
						@priceBuf
						warePrice
						((theIconI cursor?) symbol?)
					)
					p_at 3 73
					p_color 1
					p_font 123
					p_save
				)
			)
		else
			(Message MsgGet VENDOR 9 0 0 2 @priceBuf)
			(= saveBits
				(Display
					(Format
						@itemBuf
						@priceBuf
						(* wareQuantity warePrice)
						((theIconI cursor?) symbol?)
					)
					p_at
					3
					73
					p_color
					1
					p_font
					123
					p_save
				)
			)
		)
	)
)

(procedure (localproc_1426 &tmp temp0)
	(if local16
		(= temp0 0)
		(Display @temp0 p_restore local16)
	)
	(if ((inventory at: 0) amount?)
		(if commons
			(Message MsgGet VENDOR 8 0 0 2 @priceBuf)
			(= local16
				(Display
					(Format
						@itemBuf
						@priceBuf
						((inventory at: 0) amount?)
						commons
					)
					p_at
					3
					57
					p_color
					1
					p_font
					123
					p_save
				)
			)
		else
			(Message MsgGet VENDOR 8 0 0 1 @priceBuf)
			(= local16
				(Display
					(Format @itemBuf @priceBuf ((inventory at: 0) amount?))
					p_at
					3
					57
					p_color
					1
					p_font
					123
					p_save
				)
			)
		)
	else
		(Message MsgGet VENDOR 8 0 0 3 @priceBuf)
		(= local16
			(Display
				(Format @itemBuf @priceBuf commons)
				p_at
				3
				57
				p_color
				1
				p_font
				123
				p_save
			)
		)
	)
	(if local19
		(cond 
			(local18 (= local18 0))
			(local22 (= local22 0))
			(else (barter eachElementDo: #show))
		)
	else
		(= local19 1)
	)
)

(procedure (localproc_154f)
	(ShowPrice)
	(quantityIcon show:)
)

(procedure (localproc_155e)
	(localproc_135c)
	(quantityIcon show:)
)

(procedure (localproc_156d)
	(return
		(cond 
			((== ((theIconI cursor?) denomination?) 100)
				(if
					(>=
						(+ (/ commons 100) ((inventory at: 0) amount?))
						(* warePrice wareQuantity)
					)
					(return 1)
				else
					(return 0)
				)
			)
			((>= commons (* warePrice wareQuantity)) (return 1))
			(
				(>=
					(+ (* ((inventory at: 0) amount?) 100) commons)
					(* warePrice wareQuantity)
				)
				(return 1)
			)
			(else (return 0))
		)
	)
)

(class Vendor of Object
	(properties
		goods 0
		timesBargained 0
		prevBargain 0
		discount 0
		header 0
		script 0
		noun 0
	)
	
	(method (init &tmp len)
		(if (IsObject gNewCollect)
			(gNewCollect add: self)
		else
			((= gNewCollect (Collection new:)) add: self)
		)
		(= len (Message MsgSize curRoomNum noun 0 0 1))
		(= header (Memory MNewPtr len))
		(Message MsgGet curRoomNum noun 0 0 1 header)
	)
	
	(method (dispose)
		(if (IsObject gNewCollect) (gNewCollect delete: self))
		(if goods (goods dispose:))
		(if (and noun header) (Memory MDisposePtr header))
		(super dispose:)
	)
	
	(method (purchase)
		(barter init: self dispose:)
		(return (not local86))
	)
	
	(method (canStillBargain)
		(return
			(switch (theIconI message?)
				(0
					(return (not (& prevBargain $0001)))
				)
				(1
					(return (not (& prevBargain $0002)))
				)
				(2
					(return (not (& prevBargain $0004)))
				)
				(3
					(return (not (& prevBargain $0008)))
				)
			)
		)
	)
	
	(method (bargain result &tmp temp0 temp1 temp2 temp3)
		(switch result
			(0
				(|= prevBargain $0001)
			)
			(1
				(|= prevBargain $0002)
			)
			(2
				(|= prevBargain $0004)
			)
			(3
				(|= prevBargain $0008)
			)
		)
		(if (== [egoStats COMM] 550)
			(= temp0 550)
		else
			(= temp0
				(/ (* [egoStats COMM] (- 100 (* 10 timesBargained))) 100)
			)
		)
		(if (< temp0 100)
			(= temp0 100)
		)
		(++ timesBargained)
		(= temp1 ((goods at: result) price?))
		(cond 
			(
				(>=
					bargainPrice
					(= temp2
						(/ (* (- 100 (/ (- temp0 100) 4)) temp1) 100)
					)
				)
				((goods at: result) price: bargainPrice)
				(= warePrice bargainPrice)
				(= local18 1)
				(= local20 0)
				(self doBargain: bargainSUCCESS result)
			)
			((>= bargainPrice (/ temp2 2))
				((goods at: result)
					price: (- temp1 (/ (- temp1 temp2) 4))
				)
				(= warePrice
					(= bargainPrice ((goods at: result) price?))
				)
				(switch (mod (+ timesBargained 2) 3)
					(0
						(self doBargain: bargainTRY1 result)
					)
					(1
						(self doBargain: bargainTRY2 result)
					)
					(2
						(self doBargain: bargainTRY3 result)
					)
				)
			)
			((>= bargainPrice (/ temp2 4))
				(= local18 1)
				(= local20 0)
				(self doBargain: bargainFAIL result)
			)
			(else
				(= local18 1)
				(= local20 0)
				(self doBargain: bargainNODEAL result)
			)
		)
	)
	
	(method (transact)
	)
	
	(method (doBargain)
	)
	
	(method (cue &tmp temp0)
		(if local0
			(= local0 0)
			(= local22 1)
			(if (> wareQuantity 1)
				(messager say: 11 6 2 0 self VENDOR)
			else
				(messager say: 11 6 3 0 self VENDOR)
			)
		else
			(theGame setCursor: 999)
			(= local21 0)
			(if (& local9 $0001) (= wareQuantity 1))
			(if (and (& local9 $0004) local18) (= local9 2))
			(if
				(and
					(not ((theIconI cursor?) quantity?))
					(== local23 1)
				)
				(barter state: (& (barter state?) $ffdf))
			else
				(barter show:)
			)
		)
	)
	
	(method (setScript newScript)
		(if (IsObject script) (script dispose:))
		(if newScript (newScript init: self &rest))
	)
)

(class Ware
	(properties
		dynamicName 0
		name 0
		price 0
		quantity 0
		denomination 100
		symbol 0
	)
	
	(method (dispose)
		(DisposeClone self)
		(if dynamicName (Memory MDisposePtr name))
		(Memory MDisposePtr symbol)
	)
	
	(method (new param1 &tmp temp0 temp1 temp2)
		(= temp0 (Clone self))
		(if argc
			(temp0 dynamicName: 1)
			(= temp1 (Message MsgSize curRoomNum param1 38 0 1))
			(temp0 name: (Memory MNewPtr temp1))
			(Message MsgGet curRoomNum param1 38 0 1 (temp0 name?))
		)
		(temp0 symbol: (Memory MNewPtr 2))
		(return temp0)
	)
	
	(method (yourself)
		(return self)
	)
)

(instance barter of GameControls
	(properties)
	
	(method (init param1 &tmp temp0 temp1 temp2 temp3 temp4)
		(= saveCursor (theGame setCursor: 999))
		(= local19 0)
		(= window barterWin)
		(= wareQuantity 1)
		(= local9 1)
		(= theVendor param1)
		(= theIconI 0)
		(if
		(not (= temp1 ((= temp2 (theVendor goods?)) size?)))
			(= state (& state $ffdf))
		)
		(= local23 0)
		(= temp0 0)
		(= temp4 0)
		(while (< temp0 temp1)
			(if ((= temp3 (temp2 at: temp0)) quantity?)
				(self
					add:
						((barterIcon new:)
							nsLeft: [local1 temp4]
							nsRight: (+ [local1 temp4] 119)
							nsTop: [local5 temp4]
							nsBottom: (+ [local5 temp4] 16)
							message: temp0
							cursor: temp3
							yourself:
						)
				)
				(++ local23)
				(if (== (temp3 denomination?) 1)
					(Message MsgGet VENDOR 0 0 10 1 (temp3 symbol?))
				else
					(Message MsgGet VENDOR 0 0 9 1 (temp3 symbol?))
				)
				(++ temp4)
			)
			(++ temp0)
		)
		(if (== local23 1) (= local24 1) else (= local24 0))
		(return
			(if size
				(self
					add:
						acceptIcon
						bargainIcon
						refuseIcon
						addToIcon
						subtractIcon
						quantityIcon
						dummyIcon
				)
				(super init: &rest)
				(= local9 1)
				(self show:)
			else
				(= local86 1)
				(return 0)
			)
		)
	)
	
	(method (doit &tmp temp0 temp1 temp2 temp3)
		(asm
code_080b:
			pTos     state
			ldi      32
			and     
			bnt      code_08a9
			pushi    #new
			pushi    0
			pushi    #curEvent
			pushi    0
			lag      user
			send     4
			send     4
			sat      temp0
			bnt      code_08a9
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			sat      temp1
			pushi    #message
			pushi    0
			lat      temp0
			send     4
			sat      temp2
			pushi    #modifiers
			pushi    0
			lat      temp0
			send     4
			sat      temp3
			lsg      tickOffset
			pushi    0
			callk    GetTime,  0
			add     
			sag      gameTime
			lst      temp1
			ldi      256
			eq?     
			bnt      code_087a
			ldi      4
			sat      temp1
			lst      temp3
			ldi      3
			and     
			bnt      code_0860
			ldi      27
			jmp      code_0862
code_0860:
			ldi      13
code_0862:
			sat      temp2
			ldi      0
			sat      temp3
			pushi    #type
			pushi    1
			lst      temp1
			pushi    37
			pushi    1
			lst      temp2
			pushi    61
			pushi    1
			push    
			lat      temp0
			send     18
code_087a:
			pushi    #localize
			pushi    0
			lat      temp0
			send     4
			pushi    1
			lst      temp0
			callk    MapKeyToDir,  2
			pTos     state
			ldi      32
			and     
			not     
			bnt      code_0890
code_0890:
			pushi    #dispatchEvent
			pushi    1
			lst      temp0
			self     6
			bnt      code_089a
code_089a:
			lag      cuees
			bnt      code_080b
			pushi    #eachElementDo
			pushi    1
			pushi    57
			send     6
			jmp      code_080b
code_08a9:
			ret     
		)
	)
	
	(method (dispose)
		(theGame setCursor: saveCursor)
		(super dispose:)
	)
	
	(method (show &tmp [temp0 5])
		(if (& local9 $0003)
			(window top: 25 left: 35 right: 284 bottom: 136)
		else
			(window top: 25 left: 33 right: 286 bottom: 100)
		)
		(if local19
			(window open:)
			(self eachElementDo: #show)
		else
			(super show:)
		)
	)
	
	(method (hide)
		(if (or (& local9 $0004) (& local9 $0002))
			(if window (window dispose:))
		else
			(super hide:)
		)
	)
	
	(method (advance &tmp temp0 temp1)
		(asm
			ldi      1
			sat      temp1
code_0738:
			lst      temp1
			pToa     size
			le?     
			bnt      code_078e
			pushi    64
			pushi    1
			lst      temp1
			pushi    #indexOf
			pushi    1
			pTos     highlightedIcon
			self     6
			add     
			push    
			pToa     size
			mod     
			push    
			self     6
			sat      temp0
			pushi    1
			push    
			callk    IsObject,  2
			not     
			bnt      code_076b
			pushi    1
			pushi    #first
			pushi    0
			self     4
			push    
			callk    NodeValue,  2
			sat      temp0
code_076b:
			lst      temp0
			lofsa    dummyIcon
			ne?     
			bnt      code_0781
			pushi    #signal
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      4
			and     
			not     
			bnt      code_0781
code_0781:
			lst      temp1
			ldi      1
			add     
			push    
			pToa     size
			mod     
			sat      temp1
			jmp      code_0738
code_078e:
			pushi    218
			pushi    #view
			lst      temp0
			pTos     state
			ldi      32
			and     
			push    
			self     8
			ret     
		)
	)
	
	(method (retreat &tmp temp0 temp1)
		(asm
			ldi      1
			sat      temp1
code_07a3:
			lst      temp1
			pToa     size
			le?     
			bnt      code_07fa
			pushi    64
			pushi    1
			pushi    #indexOf
			pushi    1
			pTos     highlightedIcon
			self     6
			push    
			lat      temp1
			sub     
			push    
			pToa     size
			mod     
			push    
			self     6
			sat      temp0
			pushi    1
			push    
			callk    IsObject,  2
			not     
			bnt      code_07d7
			pushi    1
			pushi    #last
			pushi    0
			self     4
			push    
			callk    NodeValue,  2
			sat      temp0
code_07d7:
			lst      temp0
			lofsa    dummyIcon
			ne?     
			bnt      code_07ed
			pushi    #signal
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      4
			and     
			not     
			bnt      code_07ed
code_07ed:
			lst      temp1
			ldi      1
			add     
			push    
			pToa     size
			mod     
			sat      temp1
			jmp      code_07a3
code_07fa:
			pushi    218
			pushi    #view
			lst      temp0
			pTos     state
			ldi      32
			and     
			push    
			self     8
			ret     
		)
	)
	
	(method (dispatchEvent event &tmp newEvent temp1)
		(cond 
			(local21
				(if fastCast
					(fastCast eachElementDo: #doit)
					(if
					(and ((= newEvent (Event new:)) type?) fastCast)
						(fastCast firstTrue: #handleEvent newEvent)
					)
					(newEvent dispose:)
					(theDoits doit:)
				else
					(= gameTime (GetTime))
					(if (theVendor script?) ((theVendor script?) doit:))
					(Animate (cast elements?) TRUE)
					(if doMotionCue
						(cast eachElementDo: #motionCue)
					)
				)
			)
			((and (& local9 $0004) (== (event message?) ESC))
				(barter hide:)
				(event dispose:)
				(= local18 1)
				(theVendor cue:)
			)
			((!= (event message?) ESC) (super dispatchEvent: event &rest))
		)
	)
)

(instance barterIcon of IconItem
	(properties
		view 939
		loop 0
		cel 0
		cursor 0
		message 0
		signal $0101
	)
	
	(method (show &tmp temp0)
		(if (cursor quantity?)
			(if (and local24 (not modifiers))
				(= local9 2)
				(= modifiers 1)
				(= theIconI self)
				(= local20 (theVendor canStillBargain:))
				(= warePrice
					(= bargainPrice ((theIconI cursor?) price?))
				)
				(barter advance:)
			)
			(cond 
				((& local9 $0001) (= signal (& signal $fffb)) (self highlight: 0))
				((and (& local9 $0002) modifiers)
					(self highlight: 1)
					(= signal (| signal $0004))
					(localproc_135c)
				)
				(else (= signal (| signal $0004)) (self highlight: 1))
			)
		else
			(barter delete: self)
			(-- local23)
			(= temp0 0)
			(while (< temp0 local23)
				((barter at: temp0)
					nsLeft: [local1 temp0]
					nsRight: (+ [local1 temp0] 119)
					nsTop: [local5 temp0]
					nsBottom: (+ [local5 temp0] 16)
				)
				(++ temp0)
			)
			(self dispose:)
		)
	)
	
	(method (select)
		(= local9 2)
		(= modifiers 1)
		(= theIconI self)
		(= local20 (theVendor canStillBargain:))
		(= warePrice
			(= bargainPrice ((theIconI cursor?) price?))
		)
		(localproc_1426)
		(barter advance:)
	)
	
	(method (highlight param1 &tmp temp0 temp1 temp2 [temp3 10])
		(if (not (& local9 $0004))
			(cond 
				((& local9 $0001)
					(= temp1 0)
					(= temp2 0)
					(if param1 (= temp0 46) else (= temp0 1))
				)
				((& local9 $0002)
					(if modifiers
						(= temp1 1)
						(= temp0 46)
						(= temp2 0)
					else
						(= temp1 0)
						(= temp0 1)
						(= temp2 1)
					)
				)
			)
			(DrawCel view loop temp1 nsLeft nsTop 15)
			(Message MsgGet VENDOR 12 0 6 1 @temp3)
			(Display
				(Format
					@itemBuf
					@temp3
					(cursor name?)
					(cursor price?)
					(cursor symbol?)
				)
				p_at
				(+ nsLeft 1)
				(+ nsTop 3)
				p_color
				temp0
				p_width
				118
				p_mode
				1
				p_font
				123
				p_style
				temp2
			)
		)
	)
	
	(method (onMe)
		(if (and (& local9 $0001) (not local21))
			(super onMe: &rest)
		)
	)
)

(instance acceptIcon of IconItem
	(properties
		view 939
		loop 1
		cel 0
		signal $0101
	)
	
	(method (show)
		(cond 
			((or (& local9 $0001) (& local9 $0004)) (= signal (| signal $0004)))
			((IsObject theIconI)
				(if (and (not (localproc_156d)) (& local9 $0002))
					(= signal (| signal $0004))
				else
					(= signal (& signal $fffb))
				)
			)
		)
		(= nsLeft 3)
		(= nsTop 91)
		(= nsBottom (+ nsTop 16))
		(= nsRight (+ nsLeft 77))
		(if (not (if (& local9 $0004) (& signal $0004)))
			(self highlight: 0)
		)
	)
	
	(method (select &tmp temp0)
		(if (super select: &rest)
			(theIconI modifiers: 0)
			(= local21 1)
			(barter hide:)
			(= local9 1)
			(theVendor transact: (theIconI message?) wareQuantity)
		)
	)
	
	(method (highlight param1 &tmp temp0 temp1)
		(if (not local21)
			(if param1 (= temp0 46) else (= temp0 1))
			(if (or (& local9 $0001) (& signal $0004))
				(= temp1 1)
			else
				(= temp1 0)
			)
			(DrawCel view loop cel nsLeft nsTop 15)
			(Message MsgGet VENDOR 4 0 0 1 @priceBuf)
			(Display
				@priceBuf
				p_at
				(+ nsLeft 1)
				(+ nsTop 3)
				p_color
				temp0
				p_width
				80
				p_mode
				1
				p_font
				123
				p_style
				temp1
			)
		)
	)
	
	(method (onMe)
		(if
			(and
				(& local9 $0002)
				(not local21)
				(not (& signal $0004))
			)
			(super onMe: &rest)
		)
	)
)

(instance bargainIcon of IconItem
	(properties
		view 939
		loop 1
		cel 0
		signal $0101
	)
	
	(method (show)
		(if (or (& local9 $0001) (not local20))
			(= signal (| signal $0004))
		else
			(= signal (& signal $fffb))
		)
		(if (& local9 $0004)
			(= nsTop 55)
			(= nsLeft 88)
		else
			(= nsTop 91)
			(= nsLeft 86)
		)
		(= nsBottom (+ nsTop 16))
		(= nsRight (+ nsLeft 77))
		(self highlight: 0)
	)
	
	(method (select)
		(cond 
			((not (super select: &rest)) 0)
			((& local9 $0002)
				(theVendor timesBargained: 0)
				(= local9 4)
				(barter hide:)
				(theVendor cue:)
			)
			((& local9 $0004)
				(barter hide:)
				(= local21 1)
				(ego useSkill: 13 25)
				(theVendor bargain: (theIconI message?) bargainPrice)
			)
		)
	)
	
	(method (highlight param1 &tmp temp0 temp1)
		(if (not local21)
			(if param1 (= temp0 46) else (= temp0 1))
			(if (or (not local20) (& local9 $0001))
				(= temp1 1)
			else
				(= temp1 0)
			)
			(DrawCel view loop cel nsLeft nsTop 15)
			(if (& local9 $0004)
				(Message MsgGet VENDOR 5 0 0 1 @priceBuf)
			else
				(Message MsgGet VENDOR 5 0 0 2 @priceBuf)
			)
			(Display
				@priceBuf
				p_at
				(+ nsLeft 2)
				(+ nsTop 3)
				p_color
				temp0
				p_width
				80
				p_mode
				1
				p_font
				123
				p_style
				temp1
			)
		)
	)
	
	(method (onMe)
		(if
			(and
				(or (& local9 $0002) (& local9 $0004))
				local20
				(not local21)
			)
			(super onMe: &rest)
		)
	)
)

(instance refuseIcon of IconItem
	(properties
		view 939
		loop 1
		cel 0
		signal $0101
	)
	
	(method (show)
		(if (& local9 $0004)
			(= nsLeft 172)
			(= nsTop 55)
		else
			(= nsLeft 169)
			(= nsTop 91)
		)
		(= nsBottom (+ nsTop 16))
		(= nsRight (+ nsLeft 77))
		(self highlight: 0)
	)
	
	(method (select &tmp temp0)
		(cond 
			((not (super select: &rest)) 0)
			((& local9 $0001) (barter state: (& (barter state?) $ffdf)))
			((& local9 $0002)
				(if local24
					(barter state: (& (barter state?) $ffdf))
				else
					(theIconI modifiers: 0)
					(= local9 1)
					(= wareQuantity 1)
					(localproc_135c)
					(localproc_1426)
					(self highlight: 1)
				)
			)
			((& local9 $0004)
				(barter hide:)
				(= bargainPrice warePrice)
				(if (not (theVendor canStillBargain:)) (= local20 0))
				(= local18 1)
				(theVendor cue:)
			)
		)
	)
	
	(method (highlight param1 &tmp temp0 [temp1 20])
		(if param1 (= temp0 46) else (= temp0 1))
		(if (and (& local9 $0002) (> local23 1))
			(Message MsgGet VENDOR 6 0 4 1 @priceBuf)
		else
			(Message MsgGet VENDOR 6 0 0 1 @priceBuf)
		)
		(DrawCel view loop cel nsLeft nsTop 15)
		(Display
			@priceBuf
			p_at
			(+ nsLeft 1)
			(+ nsTop 3)
			p_color
			temp0
			p_width
			80
			p_mode
			1
			p_font
			123
		)
	)
	
	(method (onMe)
		(if (not local21) (super onMe: &rest))
	)
)

(instance addToIcon of IconItem
	(properties
		view 939
		loop 2
		cel 0
		signal $0101
	)
	
	(method (show)
		(if (& local9 $0001)
			(= signal (| signal $0004))
		else
			(= signal (& signal $fffb))
		)
		(if (& local9 $0004)
			(= nsLeft 237)
			(= nsTop 23)
		else
			(= nsLeft 233)
			(= nsTop 59)
		)
		(= nsBottom (+ nsTop 11))
		(= nsRight (+ nsLeft 12))
		(self highlight: 0)
	)
	
	(method (select)
		(cond 
			((not (super select: &rest)) (self highlight: 0))
			((& local9 $0002)
				(if
				(< wareQuantity ((theIconI cursor?) quantity?))
					(++ wareQuantity)
					(if (localproc_156d)
						(localproc_155e)
					else
						(-- wareQuantity)
					)
					(self highlight: 1)
				)
			)
			((& local9 $0004)
				(if (< bargainPrice (* 3 warePrice)) (++ bargainPrice))
				(self highlight: 1)
			)
		)
	)
	
	(method (highlight param1 &tmp temp0)
		(if (or (& local9 $0002) (& local9 $0004))
			(if param1
				(quantityIcon highlight: 1)
			else
				(quantityIcon highlight: 0)
			)
		)
		(DrawCel view loop cel nsLeft nsTop 15)
	)
	
	(method (onMe)
		(if
			(and
				(or (& local9 $0002) (& local9 $0004))
				(not local21)
			)
			(super onMe: &rest)
		)
	)
)

(instance subtractIcon of IconItem
	(properties
		view 939
		loop 3
		cel 0
		signal $0101
	)
	
	(method (show)
		(if (& local9 $0001)
			(= signal (| signal $0004))
		else
			(= signal (& signal $fffb))
		)
		(if (& local9 $0004)
			(= nsLeft 237)
			(= nsTop 37)
		else
			(= nsLeft 233)
			(= nsTop 73)
		)
		(= nsBottom (+ nsTop 11))
		(= nsRight (+ nsLeft 12))
		(self highlight: 0)
	)
	
	(method (select)
		(cond 
			((not (super select: &rest)) (self highlight: 0))
			((& local9 $0002)
				(if (> wareQuantity 1)
					(-- wareQuantity)
					(localproc_155e)
				)
				(self highlight: 1)
			)
			((& local9 $0004)
				(if (> bargainPrice 1) (-- bargainPrice))
				(self highlight: 1)
			)
		)
	)
	
	(method (highlight param1 &tmp temp0)
		(if (or (& local9 $0002) (& local9 $0004))
			(if param1
				(quantityIcon highlight: 1)
			else
				(quantityIcon highlight: 0)
			)
		)
		(DrawCel view loop cel nsLeft nsTop 15)
	)
	
	(method (onMe)
		(if
			(and
				(or (& local9 $0002) (& local9 $0004))
				(not local21)
			)
			(super onMe: &rest)
		)
	)
)

(instance quantityIcon of IconItem
	(properties
		view 939
		loop 4
		cel 0
		signal $0105
	)
	
	(method (show)
		(self highlight: 0)
	)
	
	(method (highlight param1 &tmp temp0 temp1 theBargainPrice [temp3 10])
		(if (and (& local9 $0004) (not local18))
			(= nsLeft 165)
			(= nsTop 21)
		else
			(= nsLeft 160)
			(= nsTop 57)
		)
		(= nsBottom (+ nsTop 28))
		(= nsRight (+ nsLeft 70))
		(if param1 (= temp0 46) else (= temp0 1))
		(if (& local9 $0001) (= temp1 1) else (= temp1 0))
		(DrawCel view loop cel nsLeft nsTop 15)
		(if (& local9 $0004)
			(Message MsgGet VENDOR 7 0 5 1 @priceBuf)
			(= theBargainPrice bargainPrice)
		else
			(Message MsgGet VENDOR 7 0 0 1 @priceBuf)
			(= theBargainPrice wareQuantity)
		)
		(Display
			@priceBuf
			p_at
			(+ nsLeft 1)
			(+ nsTop 3)
			p_color
			1
			p_width
			70
			p_mode
			1
			p_font
			999
			p_style
			temp1
		)
		(Message MsgGet VENDOR 12 0 7 1 @temp3)
		(Display
			(Format @itemBuf @temp3 theBargainPrice)
			p_at
			(+ nsLeft 1)
			(+ nsTop 3)
			p_color
			temp0
			p_width
			70
			p_mode
			1
			p_font
			999
			p_style
			temp1
		)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance dummyIcon of IconItem
	(properties
		view 939
		loop 0
		cel 0
		nsTop 0
		nsRight 249
	)
	
	(method (show)
		(= nsBottom (barterWin bottom?))
	)
	
	(method (select)
	)
	
	(method (highlight)
	)
)

(instance barterWin of GloryWindow

	(method (open &tmp [str 10])
		(super open: &rest)
		(Graph GFillRect
			0
			0
			(- (- bottom top) 3)
			(- (- right left) 3)
			1
			back
			-1
		)
		(Graph GDrawLine
			16
			0
			16
			(+ (- right left) 2)
			58
			-1
			-1
		)
		(if (or (& local9 $0002) (& local9 $0001))
			(Graph GDrawLine
				54
				0
				54
				(+ (- right left) 2)
				58
				-1
				-1
			)
		)
		(Graph GShowBits
			0
			0
			(- (- bottom top) 3)
			(- (- right left) 2)
			1
		)
		(Message MsgGet VENDOR 12 0 8 1 @str)
		(Display
			(Format @itemBuf @str (theVendor header?))
			p_at 0 1
			p_color 1
			p_width 249
			p_mode teJustCenter
			p_font 123
		)
		(if (& local9 $0004)
			(localproc_154f)
		else
			(localproc_1426)
		)
	)
)
