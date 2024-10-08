package com.study.member.controller;

import com.study.member.controller.docs.MemberControllerDocs;
import com.study.member.domain.event.AddressEvent;
import com.study.member.model.request.member.AddressRequestDTO;
import com.study.member.model.request.member.WishListRequestDTO;
import com.study.member.model.response.Response;
import com.study.member.model.response.member.MyPageResponseDTO;
import com.study.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController implements MemberControllerDocs {

	private final MemberService memberService;

	@PostMapping("/address")
	public Response<Void> updateAddress(@RequestBody AddressEvent request) {

		memberService.updateAddress(request);
		return Response.<Void>builder()
				.code(HttpStatus.CREATED.value())
				.message(HttpStatus.CREATED.getReasonPhrase())
				.build();
	}

	@GetMapping("/my-page/{id}")
	public Response<MyPageResponseDTO> getMemberPage(@PathVariable Long id) {

		return Response.<MyPageResponseDTO>builder()
				.data(memberService.getMemberPage(id))
				.build();
	}

	@PostMapping("/{id}")
	public Response<Void> createWishList(@PathVariable Long id, @Valid @RequestBody WishListRequestDTO request) {

		memberService.createWishList(id, request);
		return Response.<Void>builder()
				.code(HttpStatus.CREATED.value())
				.message(HttpStatus.CREATED.getReasonPhrase())
				.build();
	}

	@PatchMapping("/{id}")
	public Response<Long> modifyWishList(@PathVariable Long id, @RequestParam Integer quantity) {

		return Response.<Long>builder()
				.data(memberService.modifyWishList(id, quantity))
				.build();
	}

	@DeleteMapping("/{id}")
	public Response<Void> deleteWishList(@PathVariable Long id) {

		memberService.deleteWishList(id);
		return Response.<Void>builder()
				.build();
	}

	@PostMapping
	public Response<Void> createAddress(@Valid @RequestBody AddressRequestDTO request) {

		memberService.createAddress(request);
		return Response.<Void>builder()
				.code(HttpStatus.CREATED.value())
				.message(HttpStatus.CREATED.getReasonPhrase())
				.build();
	}
}
